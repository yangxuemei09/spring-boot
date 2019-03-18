package com.tangdi.pump.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangdi.common.common.BeanConvertor;
import com.tangdi.common.common.CodeEnum;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.pump.constant.PumpConstant;
import com.tangdi.pump.mapper.AdminMenuMapper;
import com.tangdi.pump.model.form.adminMenu.AdminMenuForm;
import com.tangdi.pump.model.form.adminMenu.AdminMenuIdForm;
import com.tangdi.pump.model.form.adminMenu.AdminMenuListForm;
import com.tangdi.pump.model.po.AdminMenu;
import com.tangdi.pump.model.vo.AdminMenuVO;
import com.tangdi.pump.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ron
 * @date 2018/12/26 11:02
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {
    @Autowired
    private AdminMenuMapper adminMenuMapper;

    /**
     * 查询菜单列表
     */
    @Override
    public PageInfo<AdminMenuVO> getMenusTree(AdminMenuListForm adminMenuListForm) throws TrafficCheckedException {
        //分页查询
        PageHelper.startPage(adminMenuListForm.getPageNum(), adminMenuListForm.getPageSize());
        adminMenuListForm.setParentMenuId(PumpConstant.TOP_MENU_ID);
        List<AdminMenu> fristList = adminMenuMapper.selectPageByCondition(adminMenuListForm);
        List<AdminMenu> allList = adminMenuMapper.selectAll();
        PageInfo page = new PageInfo(getMenuTree(fristList, allList));
        return page;
    }

    @Override
    public int addAdminMenu(AdminMenuForm adminMenuForm) throws TrafficCheckedException {
        AdminMenuListForm adminMenuListForm = new AdminMenuListForm();
        adminMenuListForm.setName(adminMenuForm.getName());
        adminMenuListForm.setParentMenuId(adminMenuForm.getParentMenuId());
        List<AdminMenu> list = adminMenuMapper.selectRepeatList(adminMenuListForm);
        if(list!=null && list.size()>0){
            throw new TrafficCheckedException(CodeEnum.AMIN_MENU_NAME);
        }
        AdminMenu record = BeanConvertor.convertor(adminMenuForm, AdminMenu.class);
        int maxSort = adminMenuMapper.getMaxSort(record.getParentMenuId());
        record.setSort(maxSort+1);
        Date date = new Date();
        record.setAddTime(date);
        record.setUpdateTime(date);
        int num = adminMenuMapper.insert(record);
        return num;
    }

    @Override
    public int editAdminMenu(AdminMenuForm adminMenuForm) throws TrafficCheckedException {
        AdminMenuListForm adminMenuListForm = new AdminMenuListForm();
        adminMenuListForm.setName(adminMenuForm.getName());
        adminMenuListForm.setParentMenuId(adminMenuForm.getParentMenuId());
        List<AdminMenu> list = adminMenuMapper.selectRepeatList(adminMenuListForm);
        if(list!=null && list.size()>0 && !adminMenuForm.getMenuId().equals(list.get(0).getMenuId())){
            throw new TrafficCheckedException(CodeEnum.AMIN_MENU_NAME);
        }
        AdminMenu record = BeanConvertor.convertor(adminMenuForm, AdminMenu.class);
        record.setUpdateTime(new Date());
        int num = adminMenuMapper.updateByPrimaryKey(record);
        return num;
    }

    @Override
    public int deleteAdminMenu(AdminMenuIdForm adminMenuIdForm) throws TrafficCheckedException {
        int num = adminMenuMapper.deleteByPrimaryKey(adminMenuIdForm.getMenuId());
        return num;
    }

    @Override
    public AdminMenu getAdminMenuById(AdminMenuIdForm adminMenuIdForm) throws TrafficCheckedException {
        AdminMenu record = adminMenuMapper.selectByPrimaryKey(adminMenuIdForm.getMenuId());
        return  record;
    }

    /**
     * 循环父级机构，组装机构的树状表格,父机构的key在sql中查询
     */
    public List<AdminMenuVO> getMenuTree(List<AdminMenu> parentMenus, List<AdminMenu> childMenus){
        List<AdminMenuVO> tree = new ArrayList<AdminMenuVO>();
        for (AdminMenu parentMenu : parentMenus) {
            tree.add(parseForOneParent(parentMenu, childMenus));
        }
        return tree;
    }

    /**
     * 组装一个父级机构的树,子机构的key在sql中查询
     */
    public AdminMenuVO parseForOneParent(AdminMenu parentMenu, List<AdminMenu> childMenus) {
        AdminMenuVO parentTree = BeanConvertor.convertor(parentMenu, AdminMenuVO.class);
        List<AdminMenuVO> tree = new ArrayList<AdminMenuVO>();
        for (int i = 0; i < childMenus.size(); i++) {
            AdminMenu childMenu = childMenus.get(i);
            if (parentMenu.getMenuId().equals(childMenu.getParentMenuId())) {
                tree.add(parseForOneParent(childMenu, childMenus));
            }
        }
        if (tree.size() > 0) {
            parentTree.setChildren(tree);
        }
        return parentTree;
    }

}
