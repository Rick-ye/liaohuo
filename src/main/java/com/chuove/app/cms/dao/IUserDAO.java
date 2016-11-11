package com.chuove.app.cms.dao;

import java.util.List;
import java.util.Map;

import com.chuove.app.cms.model.PermissionDo;
import com.chuove.app.cms.model.RoleDo;
import com.chuove.app.cms.model.RolePermission;
import com.chuove.app.cms.model.UserDo;
import com.chuove.app.cms.model.UserRole;


public interface IUserDAO {
	public void saveUser(UserDo userDo);

	public List<UserDo> findAllUser();

	public UserDo findUserByUserName(String userName);

	public List<UserDo> findUserByCreateUserId(Integer userId);
	
	public List<UserDo> findUserByRoleName(Map<String, Object> map);

	public UserDo findUserByUserId(Integer userId);
	
	public void updateUserMerchantId(Map<String, Object> map);
	
	public void updateUserRegionId(Map<String, Object> map);

	public void updateUserStatus(Map<String, Object> map);

	public void updateUserInfo(UserDo userDo);
	
	public void deleteUserByUserId(Integer userId);
	
	public void updateUserPwd(Map<String, Object> map);

	public List<RoleDo> findAllRole();

	public void saveRoleDo(RoleDo roleDo);


	public RoleDo findRoleByRoleId(Integer roleId);

	public void updateRoleDoInfo(RoleDo roleDo);

	public void deleteRoleDo(Integer roleId);

	public void savePermissionDo(PermissionDo permissionDo);

	public RoleDo findPermissionByPermissionId(Integer permissionId);

	public void updatePermissionDoInfo(PermissionDo permissionDo);

	public void deletePermissionDo(Integer permissionId);
	
	
    public PermissionDo findSuperParentPermission();
	public List<PermissionDo> findPermissionDoTree(Integer parentId);

	public List<PermissionDo> findOwnPermissionByCondition(List<Integer> list);
	public List<PermissionDo> findOwnPermissionDoTree(Map<String, Object> map);

	public void saveUserRole(UserRole userRole);

	public void updateUserRole(Map<String, Object> map);

	public UserRole findUserRole(Integer userId);

	public void saveRolePermission(RolePermission rolePermission);

	public RolePermission findRolePersission(Map<String, Object> map);

	public void deleteRolePermission(Integer roleId);
	
}
