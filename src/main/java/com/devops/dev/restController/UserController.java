package com.devops.dev.restController;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.dev.dao.ClassLevelTypeDao;
import com.devops.dev.dao.UserDao;
import com.devops.dev.domainObject.ClassLevelType;
import com.devops.dev.domainObject.StudentDetail;
import com.devops.dev.domainObject.UserRoles;
import com.devops.dev.domainObject.Users;
import com.devops.dev.domainObject.WizardModal;
import com.devops.dev.util.ROLE;
import com.devops.dev.util.UserNamePassswordUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserController {

	ArrayList<String> STUDENT_CONFIG_FILED_LIST = new ArrayList<String>(Arrays.asList("board","classLevel","standard","language","totalFees","feesPaid","feesRemaining"));
	
	@Autowired
	private ClassLevelTypeDao classLevelTypeDao;
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping("/addStudent")
	public String classLevelTypeList(@RequestBody WizardModal[] wizardFormArray) throws JsonProcessingException {

		int classLevelId = 0, boardId = 0;
		String className = null, language = null, firstName = null, lastName = null;
		try {
			Users userObj = new Users();
			Class ftClass = userObj.getClass();
			Field[] fA = ftClass.getFields();
			for(Field f : fA) {
				f.setAccessible(true);
				System.out.println(f.getName());
			}
			for(int i = 0; i < wizardFormArray.length; i++) {
				
				if(STUDENT_CONFIG_FILED_LIST.contains(wizardFormArray[i].getName())) {
					System.out.println(wizardFormArray[i].getName() + " : " + wizardFormArray[i].getValue());
					if(wizardFormArray[i].getName().equalsIgnoreCase("board")) {
						boardId = Integer.valueOf(wizardFormArray[i].getValue());
					} else if(wizardFormArray[i].getName().equalsIgnoreCase("classLevel")) {
						classLevelId = Integer.valueOf(wizardFormArray[i].getValue());
					} else if(wizardFormArray[i].getName().equalsIgnoreCase("standard")) {
						className = wizardFormArray[i].getValue();
					} if(wizardFormArray[i].getName().equalsIgnoreCase("language")) {
						language = wizardFormArray[i].getValue();
					}
				} else {
					Field fl = ftClass.getDeclaredField(wizardFormArray[i].getName());
					String typeClass = fl.getType().getTypeName();
					fl.setAccessible(true);
					if(typeClass.equalsIgnoreCase("java.util.Date")) {
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
						Date dob = (Date)formatter.parse(wizardFormArray[i].getValue());
						fl.set(userObj, dob);
					} else {
						if(wizardFormArray[i].getName().equalsIgnoreCase("firstName")) {
							firstName = wizardFormArray[i].getValue();
						} else if(wizardFormArray[i].getName().equalsIgnoreCase("lastName")) {
							lastName = wizardFormArray[i].getValue();
						}
						fl.set(userObj, wizardFormArray[i].getValue());
					}
				}
			}
			userObj.setUsername(UserNamePassswordUtil.getUserName(firstName, lastName));
			userObj.setPassword(UserNamePassswordUtil.getUserPassword(firstName, lastName));
			userDao.save(userObj);
			
			UserRoles userRole = new UserRoles();
			userRole.setRole(ROLE.ROLE_STUDENT.toString());
			userRole.setUsers(userObj);
			userDao.saveRole(userRole);
			
			ClassLevelType classLevelType = classLevelTypeDao.getClassLevelType(classLevelId, boardId, className, language);
			StudentDetail studentDetail = new StudentDetail();
			studentDetail.setClassLevelType(classLevelType);
			studentDetail.setUsers(userObj);
			
			userDao.saveStudentDetail(studentDetail);
			
			System.out.println(userObj.getFirstName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
