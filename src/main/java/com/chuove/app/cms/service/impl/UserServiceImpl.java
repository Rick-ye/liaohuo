package com.chuove.app.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuove.app.cms.dao.IUserDAO;
import com.chuove.app.cms.model.PermissionDo;
import com.chuove.app.cms.model.RoleDo;
import com.chuove.app.cms.model.RolePermission;
import com.chuove.app.cms.model.UserDo;
import com.chuove.app.cms.model.UserRole;
import com.chuove.app.cms.service.IUserService;



@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public void saveUser(UserDo userDo) {
		userDAO.saveUser(userDo);

	}

	@Override
	public UserDo findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDAO.findUserByUserName(userName);
	}

	@Override
	public List<UserDo> findAllUser() {
		return userDAO.findAllUser();
	}

	@Override
	public List<UserDo> findUserByCreateUserId(Integer userId) {
		return userDAO.findUserByCreateUserId(userId);
	}

	@Override
	public List<UserDo> findUserByRoleName(Integer merchantId, String roleName) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (merchantId != null)
			map.put("merchantId", merchantId);
		map.put("roleName", roleName);
		return userDAO.findUserByRoleName(map);
	}

	@Override
	public UserDo findUserByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userDAO.findUserByUserId(userId);
	}

	@Override
	public void updateUserStatus(Integer userId, String status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userStatus", status);
		map.put("userId", userId);
		userDAO.updateUserStatus(map);
	}

	@Override
	public void updateUserInfo(UserDo userDo) {
		userDAO.updateUserInfo(userDo);
	}

	@Override
	public void deleteUserByUserId(Integer userId) {
		userDAO.deleteUserByUserId(userId);
	}

	@Override
	public void updateUserMerchantId(Integer merchantId, Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchantId", merchantId);
		map.put("userId", userId);
		userDAO.updateUserMerchantId(map);

	}

	@Override
	public void updateUserRegionId(Integer regionId, Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionId", regionId);
		map.put("userId", userId);
		userDAO.updateUserRegionId(map);
	}

	@Override
	public void updateUserPwd(Integer userId, String userPwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userPwd", userPwd);
		userDAO.updateUserPwd(map);
	}

	@Override
	public List<RoleDo> findAllRole() {
		return userDAO.findAllRole();
	}

	@Override
	public void saveRoleDo(RoleDo roleDo) {
		userDAO.saveRoleDo(roleDo);

	}

	@Override
	public RoleDo findRoleByRoleId(Integer roleId) {
		return userDAO.findRoleByRoleId(roleId);
	}

	@Override
	public void updateRoleDoInfo(RoleDo roleDo) {
		userDAO.updateRoleDoInfo(roleDo);

	}

	@Override
	public void deleteRoleDo(Integer roleId) {
		userDAO.deleteRoleDo(roleId);

	}

	@Override
	public void savePermissionDo(PermissionDo permissionDo) {
		userDAO.savePermissionDo(permissionDo);

	}

	@Override
	public RoleDo findPermissionByPermissionId(Integer permissionId) {
		return userDAO.findPermissionByPermissionId(permissionId);
	}

	@Override
	public void updatePermissionDoInfo(PermissionDo permissionDo) {
		userDAO.updatePermissionDoInfo(permissionDo);

	}

	@Override
	public void deletePermissionDo(Integer permissionId) {
		userDAO.deletePermissionDo(permissionId);
	}

	@Override
	public List<PermissionDo> findPermissionDoTree(Integer parentId) {
		return userDAO.findPermissionDoTree(parentId);
	}

	@Override
	public List<PermissionDo> findOwnPermissionDoTree() {
		List<PermissionDo> permissionList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		permissionList = userDAO.findOwnPermissionDoTree(map);

		return permissionList;
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		userDAO.saveUserRole(userRole);

	}

	@Override
	public void updateUserRole(Integer userId, Integer roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("userId", userId);
		userDAO.updateUserRole(map);
	}

	@Override
	public UserRole findUserRole(Integer userId) {

		return userDAO.findUserRole(userId);
	}

	@Override
	public void saveRolePermission(RolePermission rolePermission) {
		userDAO.saveRolePermission(rolePermission);
	}

	public RolePermission findRolePersission(Integer roleId, Integer permissionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("permissionId", permissionId);
		return userDAO.findRolePersission(map);
	}

	@Override
	public void deleteRolePermission(Integer roleId) {
		userDAO.deleteRolePermission(roleId);

	}

	@Override
	public List<PermissionDo> findOwnPermissionByCondition(List<Integer> list) {

		return userDAO.findOwnPermissionByCondition(list);
	}
}
