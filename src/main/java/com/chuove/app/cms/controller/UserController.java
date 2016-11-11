package com.chuove.app.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.chuove.app.cms.common.context.RootContext;
import com.chuove.app.cms.common.utils.Md5;
import com.chuove.app.cms.common.utils.StringUtils;
import com.chuove.app.cms.model.PermissionDo;
import com.chuove.app.cms.model.RoleDo;
import com.chuove.app.cms.model.RolePermission;
import com.chuove.app.cms.model.UserDo;
import com.chuove.app.cms.model.UserRole;
import com.chuove.app.cms.service.IUserService;
import com.chuove.app.cms.code.ActionStatus;
import com.chuove.app.cms.code.Settings;
import com.chuove.app.cms.code.UserStatus;
import com.chuove.app.cms.code.UserType;

@Controller
@RequestMapping(value = "admin")
public class UserController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "userList")
	public String userList(ModelMap model) {
		try {
			List<UserDo> list = this.userService.findUserByCreateUserId(getUserId());
			model.addAttribute("userDo", list);
		} catch (Exception e) {
			logger.error("userList error", e);
		}
		return "admin_user_list";
	}

	@RequestMapping(value = "userStop", method = RequestMethod.POST)
	public void userStop(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.userService.updateUserStatus(Integer.valueOf(request.getParameter("userId")),
					UserStatus.FORBIDDEN.getCode());
			setStatus(ActionStatus.SUCCESS, "成功");
			renderData(response);
		} catch (Exception e) {
			logger.error("userList error", e);
		}
	}

	@RequestMapping(value = "userStart", method = RequestMethod.POST)
	public void userStart(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.userService.updateUserStatus(Integer.valueOf(request.getParameter("userId")),
					UserStatus.ACTIVE.getCode());
			setStatus(ActionStatus.SUCCESS, "成功");
			renderData(response);
		} catch (Exception e) {
			logger.error("userList error", e);
		}
	}

	@RequestMapping(value = "userAdd")
	public String userAdd(ModelMap model) {
		try {
			model.put("roleList", getRoleList());
		} catch (Exception e) {
			logger.error("userList error", e);
		}
		return "admin_user_add";
	}

	@RequestMapping(value = "userEdit", method = RequestMethod.GET)
	public String userEdit(HttpServletRequest request, ModelMap model) {
		try {
			UserDo userDo = userService.findUserByUserId(Integer.valueOf(request.getParameter("userId")));
			model.put("userDo", userDo);
			model.put("roleList", getRoleList());
		} catch (Exception e) {
			logger.error("userList error", e);
		}
		return "admin_user_edit";
	}

	@RequestMapping(value = "userDel", method = RequestMethod.POST)
	@ResponseBody
	public void userDel(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserDo userDo = userService.findUserByUserId(Integer.valueOf(request.getParameter("userId")));
			boolean canDel=false;
			if (userDo != null) {
				if (userDo.getRole() != null) {
				if(canDel){
				setStatus(ActionStatus.SUCCESS, "已删除");
				userService.deleteUserByUserId(userDo.getUserId());
				}
			}
			}
		} catch (Exception e) {
			setStatus(ActionStatus.FAIL, "删除失败");
			logger.error("userList error", e);
		}
		renderData(response);
	}

	@RequestMapping(value = "userDoAdd")
	public void userDoAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userName = request.getParameter("mobileNumber");
			UserDo user = userService.findUserByUserName(userName);
			if (user != null) {
				setStatus(ActionStatus.FAIL, "已存在相同账户");
			} else {
				String mobileNumber = request.getParameter("mobileNumber");
				String realName = request.getParameter("realName");
				String userEmail = request.getParameter("userEmail");
				user = new UserDo();
				user.setCreateTime(new Date());
				user.setMobileNumber(mobileNumber);
				user.setUserName(userName);
				user.setRealName(realName);
				user.setUserPwd(Md5.encode(RootContext.instance().getString(Settings.DEFAULT_PASSWD)));
				user.setUserEmail(userEmail);
				user.setUserStatus(UserStatus.ACTIVE.getCode());
				UserDo adminUser = getUserDo();
				user.setCreateUserId(adminUser == null ? 0 : adminUser.getUserId());
				userService.saveUser(user);
				if (StringUtils.isNotEmpty(request.getParameter("adminRole"))) {
					UserRole ur = new UserRole();
					ur.setRoleId(Integer.valueOf(request.getParameter("adminRole")));
					ur.setUserId(user.getUserId());
					userService.saveUserRole(ur);
				}
				setStatus(ActionStatus.SUCCESS, "添加成功");
			}
		} catch (Exception e) {
			setStatus(ActionStatus.FAIL, "系统异常");
			logger.error("userList error", e);
		}
		renderData(response);
	}

	@RequestMapping(value = "updatePwd")
	public String updatePwd(HttpServletRequest request, ModelMap model) {
		model.put("userId", request.getParameter("userId"));
		return "admin_update_pwd";
	}

	@RequestMapping(value = "doUpdatePwd", method = RequestMethod.POST)
	@ResponseBody
	public void doUpdatePwd(HttpServletRequest request, HttpServletResponse response) {
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		try {
			UserDo ud = userService.findUserByUserId(userId);
			if (ud != null) {
				if (ud.getUserPwd().equals(Md5.encode(oldPwd))) {
					userService.updateUserPwd(userId, Md5.encode(newPwd));
					setStatus(ActionStatus.SUCCESS, "密码修改成功");
				} else {
					setStatus(ActionStatus.FAIL, "原始密码错误");
				}
			}
		} catch (Exception e) {
			logger.error("error", e);
		}
		renderData(response);
	}

	@RequestMapping(value = "userDoEdit")
	public void userDoEdit(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userId = request.getParameter("userId");
			if (StringUtils.isBlank(userId)) {
				setStatus(ActionStatus.FAIL, "参数错误");
			} else {
				String userName = request.getParameter("userName");
				UserDo user = userService.findUserByUserName(userName);
				if (user != null && user.getUserId().intValue() != Integer.valueOf(userId).intValue()) {
					setStatus(ActionStatus.FAIL, "存在相同账户");
				}
				user = userService.findUserByUserId(Integer.valueOf(userId));
				String mobileNumber = request.getParameter("mobileNumber");
				String userEmail = request.getParameter("userEmail");
				user.setMobileNumber(mobileNumber);
				user.setUserName(userName);
				user.setUserEmail(userEmail);
				userService.updateUserInfo(user);

				if (StringUtils.isNotEmpty(request.getParameter("adminRole"))) {
					if (user.getRole() == null) {
						UserRole ur = new UserRole();
						ur.setRoleId(Integer.valueOf(request.getParameter("adminRole")));
						ur.setUserId(user.getUserId());
						userService.saveUserRole(ur);
					} else if (user.getRole().getRoleId().intValue() != Integer
							.valueOf(request.getParameter("adminRole")).intValue()) {
						userService.updateUserRole(user.getUserId(),
								Integer.valueOf(request.getParameter("adminRole")));
					}

				}
				setStatus(ActionStatus.SUCCESS);
			}
		} catch (Exception e) {
			setStatus(ActionStatus.FAIL, "系统异常");
			logger.error("error", e);
		}
		renderData(response);
	}

	@RequestMapping(value = "roleList")
	public String roleList(ModelMap model) {
		try {

			model.put("roleDo", getRoleList());
			model.put("ud", getUserDo());
		} catch (Exception e) {
			logger.error("userList error", e);
		}
		return "admin_role_list";
	}

	private List<RoleDo> getRoleList() {
		List<RoleDo> list = userService.findAllRole();
		return list;
	}

	@RequestMapping(value = "roleAdd")
	public String roleAdd(ModelMap model) {
		List<UserType> userTypeList = new ArrayList<UserType>();
		userTypeList.add(UserType.COMMONREADER);
		userTypeList.add(UserType.EDITOR);
		model.put("userTypeList", userTypeList);
		return "admin_role_add";
	}

	@RequestMapping(value = "roleEdit", method = RequestMethod.GET)
	public String roleEdit(HttpServletRequest request, ModelMap model) {
		try {
			RoleDo role = userService.findRoleByRoleId(Integer.valueOf(request.getParameter("roleId")));
			List<UserType> userTypeList = new ArrayList<UserType>();
			userTypeList.add(UserType.COMMONREADER);
			userTypeList.add(UserType.EDITOR);
			model.put("userTypeList", userTypeList);
			model.put("role", role);
		} catch (Exception e) {
			logger.error("userList error", e);
		}
		return "admin_role_edit";
	}

	@RequestMapping(value = "roleOwnPermission", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String roleOwnPermission(HttpServletRequest request, HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		String s = "[]";
		try {
			List<PermissionDo> permissionList = userService.findOwnPermissionDoTree();
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			if (permissionList != null) {
				for (PermissionDo pd : permissionList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", pd.getPermissionId());
					map.put("pId", pd.getParentId());
					map.put("name", pd.getPermissionAlias());
					map.put("open", true);
					map.put("checked", false);
					if (StringUtils.isNotEmpty(roleId)) {
						RolePermission rp = userService.findRolePersission(Integer.valueOf(roleId),
								pd.getPermissionId());
						if (rp != null) {
							map.put("checked", true);
						}
					}
					result.add(map);
					if (pd.getPermissionList() != null) {
						print2(pd.getPermissionList(), result, roleId);
					}
				}
			}
			s = JSON.toJSONString(result);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	private void print2(List<PermissionDo> permissionList, List<Map<String, Object>> result, String roleId) {
		if (permissionList == null || permissionList.isEmpty())
			return;
		for (PermissionDo pd : permissionList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", pd.getPermissionId());
			map.put("pId", pd.getParentId());
			map.put("name", pd.getPermissionAlias());
			map.put("open", true);
			map.put("checked", false);
			if (StringUtils.isNotEmpty(roleId)) {
				RolePermission rp = userService.findRolePersission(Integer.valueOf(roleId), pd.getPermissionId());
				if (rp != null) {
					map.put("checked", true);
				}
			}
			result.add(map);
			if (pd.getPermissionList() != null) {
				print2(pd.getPermissionList(), result, roleId);
			}
		}
	}

	@RequestMapping(value = "roleDoAdd")
	public void roleDoAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleName = request.getParameter("roleName");
			String roleAlias = request.getParameter("roleAlias");
			String roleDesc = request.getParameter("roleDesc");
			RoleDo role = new RoleDo();
			role.setCreateTime(new Date());
			role.setRoleName(roleName);
			role.setRoleAlias(roleAlias);
			role.setRoleDesc(roleDesc);
			role.setUpdateTime(new Date());
			UserDo adminUser = getUserDo();
			userService.saveRoleDo(role);
			role.setRoleName(roleName + "_" + role.getRoleId());
			userService.updateRoleDoInfo(role);
			setRolePermission(request);
			setStatus(ActionStatus.SUCCESS);
		} catch (Exception e) {
			setStatus(ActionStatus.FAIL, "系统异常");
			logger.error("userList error", e);
		}
		renderData(response);
	}

	@RequestMapping(value = "roleDoEdit")
	public void roleDoEdit(HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleId = request.getParameter("roleId");
			if (StringUtils.isBlank(roleId)) {
				setStatus(ActionStatus.FAIL, "参数错误");
			} else {
				RoleDo role = userService.findRoleByRoleId(Integer.valueOf(roleId));
				if (role == null) {
					setStatus(ActionStatus.FAIL, "参数错误");
				} else {
					String roleName = request.getParameter("roleName");
					String roleAlias = request.getParameter("roleAlias");
					String roleDesc = request.getParameter("roleDesc");
					role.setRoleName(roleName + "_" + role.getRoleId());
					role.setRoleAlias(roleAlias);
					role.setRoleDesc(roleDesc);
					role.setUpdateTime(new Date());
					userService.updateRoleDoInfo(role);
					setRolePermission(request);
					setStatus(ActionStatus.SUCCESS);
				}
			}
		} catch (Exception e) {
			setStatus(ActionStatus.FAIL, "系统异常");
			logger.error("userList error", e);
		}
		renderData(response);
	}

	private void setRolePermission(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		if (StringUtils.isBlank(roleId)) {
			return;
		}
		String permissionIds = request.getParameter("permissionIds");
		if (StringUtils.isBlank(permissionIds)) {
			return;
		}
		String[] pers = permissionIds.split(",");

		userService.deleteRolePermission(Integer.valueOf(roleId));
		for (String p : pers) {
			if (StringUtils.isNotEmpty(p)) {
				RolePermission rp = new RolePermission();
				rp.setPermissionId(Integer.valueOf(p));
				rp.setRoleId(Integer.valueOf(roleId));
				userService.saveRolePermission(rp);
			}
		}
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		if (securityManager != null) {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "roleDoDel")
	public void roleDoDel(HttpServletRequest request, HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		userService.deleteRoleDo(Integer.valueOf(roleId));
	}


	@RequestMapping(value = "permissionList")
	public String permissionList(HttpServletRequest request, ModelMap model) {
		List<PermissionDo> permissionList = userService
				.findPermissionDoTree(Integer.valueOf(request.getParameter("parentId")));
		model.put("permissionList", permissionList);
		return "admin_permission_list";
	}

	@RequestMapping(value = "permissionList2", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String permissionList2(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<PermissionDo> permissionList = userService
				.findPermissionDoTree(Integer.valueOf(request.getParameter("parentId")));
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (permissionList != null) {
			for (PermissionDo pd : permissionList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", pd.getPermissionId());
				map.put("pId", pd.getParentId());
				map.put("name", pd.getPermissionAlias());
				map.put("open", true);
				result.add(map);
				if (pd.getPermissionList() != null) {
					print(pd.getPermissionList(), result);
				}
			}
		}
		String s = JSON.toJSONString(result);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		return s;
	}

	private void print(List<PermissionDo> permissionList, List<Map<String, Object>> result) {
		if (permissionList == null || permissionList.isEmpty())
			return;
		for (PermissionDo pd : permissionList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", pd.getPermissionId());
			map.put("pId", pd.getParentId());
			map.put("name", pd.getPermissionAlias());
			map.put("open", true);
			result.add(map);
			if (pd.getPermissionList() != null) {
				print(pd.getPermissionList(), result);
			}
		}
	}
}
