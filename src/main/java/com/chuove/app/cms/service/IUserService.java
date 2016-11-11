package com.chuove.app.cms.service;

import java.util.List;

import com.chuove.app.cms.model.PermissionDo;
import com.chuove.app.cms.model.RoleDo;
import com.chuove.app.cms.model.RolePermission;
import com.chuove.app.cms.model.UserDo;
import com.chuove.app.cms.model.UserRole;



public interface IUserService {
	public void saveUser(UserDo userDo);

	public List<UserDo> findAllUser();

	public List<UserDo> findUserByCreateUserId(Integer userId);
	
	public List<UserDo> findUserByRoleName(Integer merchantId,String roleName);

	public UserDo findUserByUserName(String userName);

	public UserDo findUserByUserId(Integer userId);

	public void updateUserStatus(Integer userId, String status);

	public void updateUserInfo(UserDo userDo);
	
   public void updateUserMerchantId(Integer merchantId,Integer userId);
	
	public void updateUserRegionId(Integer regionId,Integer userId);

	public void deleteUserByUserId(Integer userId);

	public void updateUserPwd(Integer userId, String userPwd);

	public List<RoleDo> findAllRole();

	public void saveRoleDo(RoleDo roleDo);

	public RoleDo findRoleByRoleId(Integer roleId);

	public void updateRoleDoInfo(RoleDo roleDo);

	public void deleteRoleDo(Integer roleId);

	public void savePermissionDo(PermissionDo permissionDo);

	public RoleDo findPermissionByPermissionId(Integer permissionId);

	public void updatePermissionDoInfo(PermissionDo permissionDo);

	public void deletePermissionDo(Integer permissionId);

	public List<PermissionDo> findPermissionDoTree(Integer parentId);

	public List<PermissionDo> findOwnPermissionDoTree();
	public List<PermissionDo> findOwnPermissionByCondition(List<Integer> list);
	public void saveUserRole(UserRole userRole);

	public void updateUserRole(Integer userId, Integer roleId);

	public UserRole findUserRole(Integer userId);

	public void saveRolePermission(RolePermission rolePermission);

	public RolePermission findRolePersission(Integer roleId, Integer permissionId);

	public void deleteRolePermission(Integer roleId);
}
