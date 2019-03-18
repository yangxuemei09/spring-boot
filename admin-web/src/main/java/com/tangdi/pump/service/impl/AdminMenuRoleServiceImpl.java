package com.tangdi.pump.service.impl;


import cn.hutool.core.util.StrUtil;
import com.tangdi.common.common.BeanConvertor;
import com.tangdi.common.exception.TrafficCheckedException;
import com.tangdi.common.utils.IdGenUtils;
import com.tangdi.pump.constant.PumpConstant;
import com.tangdi.pump.mapper.AdminMenuMapper;
import com.tangdi.pump.mapper.AdminMenuRoleMapper;
import com.tangdi.pump.mapper.AdminRoleMapper;
import com.tangdi.pump.model.form.adminMenuRole.AdminMenuRoleAddForm;
import com.tangdi.pump.model.form.adminMenuRole.AdminMenuRoleForm;
import com.tangdi.pump.model.po.AdminMenu;
import com.tangdi.pump.model.po.AdminMenuRole;
import com.tangdi.pump.model.vo.AdminMenuMetaVo;
import com.tangdi.pump.model.vo.AdminMenuRoleVO;
import com.tangdi.pump.service.AdminMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ron
 * @date 2018/12/26 11:02
 */
@Service
public class AdminMenuRoleServiceImpl implements AdminMenuRoleService {
    @Autowired
    private AdminMenuMapper adminMenuMapper;
    @Autowired
    private AdminMenuRoleMapper adminMenuRoleMapper;
    /**
     * 分配菜单
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int assignAdminMenu(AdminMenuRoleAddForm adminMenuRoleAddForm) throws TrafficCheckedException {
        // 删除该角色绑定的菜单数据
        String roleId = adminMenuRoleAddForm.getRoleId();
        adminMenuRoleMapper.deleteByRoleId(roleId);
        // 新增权限表
        String[] menuIds = adminMenuRoleAddForm.getMenuIds().split(",");
        List<AdminMenuRole> adminMenuRoleList = new ArrayList<AdminMenuRole>();
        AdminMenuRole roleMenu = null;
        Date date = new Date();
        for(String menuId: menuIds){
            roleMenu = new AdminMenuRole();
            roleMenu.setRoleMenuId(IdGenUtils.getUUID());
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setAddTime(date);
            roleMenu.setUpdateTime(date);
            adminMenuRoleList.add(roleMenu);
        }
        return adminMenuRoleMapper.insertList(adminMenuRoleList);

    }
    /**
     * 根据角色获取菜单树
     */
    @Override
    public List<AdminMenuRoleVO> getMenuListByRole(AdminMenuRoleForm adminMenuRoleForm) throws TrafficCheckedException {
        List<AdminMenu> allList = adminMenuMapper.selectListByRoleId(adminMenuRoleForm);
        adminMenuRoleForm.setParentMenuId(PumpConstant.TOP_MENU_ID);
        List<AdminMenu> fristList = adminMenuMapper.selectListByRoleId(adminMenuRoleForm);
        List<AdminMenuRoleVO> list = getMenuTree(fristList, allList);
        return list;
    }


    /**
     * 循环父级机构，组装机构的树状表格,父机构的key在sql中查询
     */
    public List<AdminMenuRoleVO> getMenuTree(List<AdminMenu> parentMenus, List<AdminMenu> childMenus){
        List<AdminMenuRoleVO> tree = new ArrayList<AdminMenuRoleVO>();
        for (AdminMenu parentMenu : parentMenus) {
            tree.add(parseForOneParent(parentMenu, childMenus));
        }
        return tree;
    }

    /**
     * 组装一个父级机构的树,子机构的key在sql中查询
     */
    public AdminMenuRoleVO parseForOneParent(AdminMenu parentMenu, List<AdminMenu> childMenus) {
        AdminMenuRoleVO parentTree = BeanConvertor.convertor(parentMenu, AdminMenuRoleVO.class);
        if(PumpConstant.TOP_MENU_ID.equals(parentMenu.getParentMenuId())){
            parentTree.setPath("/" + parentMenu.getPath());
            parentTree.setRedirect("noredirect");
        }
        parentTree.setMeta(new AdminMenuMetaVo(parentMenu.getName(),parentMenu.getIcon()));
        List<AdminMenuRoleVO> tree = new ArrayList<AdminMenuRoleVO>();
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
