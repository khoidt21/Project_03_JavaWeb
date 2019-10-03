package com.org.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;

import org.apache.catalina.servlet4preview.ServletContext;

import com.org.entity.User;

public class UsersData {

	private static UsersData instance;
	private final String characters = ",";
	private String message;

	private final String path = "D:\\employee.txt";

	private static HashMap<String, String> users;
	private static ArrayList<String> usersOnline;

	private UsersData() {
	}

	public static UsersData getInstance() {
		if (instance == null) {
			instance = new UsersData();
		}
		return instance;
	}

	public String getPathData() {
		return path;
	}

	public String getSpChar() {
		return characters;
	}

	public HashMap<String, String> getData() {
		return users;
	}

	public void loadDataByFileToHashMap() {
		
		if (users != null) {
			return;
		}
		users = new HashMap<>();
		usersOnline = new ArrayList<>();
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(path);
			bufferedReader = new BufferedReader(fileReader);
			String str;

			while ((str = bufferedReader.readLine()) != null) {
				String[] bUser = str.split(characters);

				if (bUser.length >= 2) {
					User user = new User(bUser[0], bUser[1]);
					if (user.validationUser() && !hasUser(bUser[0])) {
						users.put(user.getUsername(), user.getPassword());
					}
				}
			}
			bufferedReader.close();
		} catch (IOException ioexception) {
			System.out.println(ioexception);
		}
	}

	// Check and get information user
	public boolean checkUser(String username, String password) {
		return checkUser(new User(username, password));
	}

	// Check data user in HashMap
	public boolean checkUser(User user) {
		if (users.isEmpty()) {
			user.addError("Username is not hash map.");
			return false;
		}
		if (users.containsKey(user.getUsername())) {
			if (users.get(user.getUsername()).equals(user.getPassword())) {
				return true;
			}
			user.addError("Password incorrect.");
			return false;
		}
		user.addError("Username not found.");
		return false;
	}

	// Check user in HashMap
	public boolean hasUser(String username) {
		return users.containsKey(username);
	}

	// Add user to HashMap
	public void addUser(String username, String password) {
		users.put(username, password);
	}

	public String getMessage() {
		return message;
	}

	public void saveUsers() {
		try {		
			FileOutputStream out = new FileOutputStream(path);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			if (users != null) {
				for (Map.Entry m : users.entrySet()) {
					bw.write(m.getKey() + characters + m.getValue() + "\n");
				}
			}
			//System.out.println("save user to hashmap sccess");
			bw.close();
		} catch (IOException e) {
		}
	}

	public String usernamesToString() {
		ArrayList<String> m = new ArrayList<>();
		for (Map.Entry mm : users.entrySet()) {
			m.add((String) mm.getKey());
		}
		return String.join(", ", m);
	}

	public void addUsersOnline(String username) {
		if (!usersOnline.contains(username)) {
			usersOnline.add(username);
		}
	}

	// get count user online
	public ArrayList<String> getUsersOnline() {
		if (usersOnline != null)
			return usersOnline;
		return new ArrayList<>();
	}
}
