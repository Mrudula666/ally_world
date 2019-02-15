package com.capgemini.allyworld.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.allyworld.website.entity.Profile;

@Controller
public class WebsiteController {

	@Autowired
	private RestTemplate restTemplate;

	Profile userProfile;
	int profileId;
	List<Profile> friendsList;

	@RequestMapping("/")
	public String getHomePage(Model model) {
		model.addAttribute("profile", new Profile());
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView userRegistration(@ModelAttribute Profile profile) {
		System.out.println("inside registration");
		System.out.println("Before" + profile);
		ResponseEntity<Profile> updatedProfile = restTemplate.postForEntity("http://localhost:2013/profiles", profile,
				Profile.class);
		System.out.println("after" + updatedProfile);
		return new ModelAndView("index", "message", "Registration Success");
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView autheticateUser(@ModelAttribute Profile profile) {
		// System.out.println("Inside website controller");
		ResponseEntity<Profile> response = restTemplate.postForEntity("http://localhost:2013/profiles/authenticate",
				profile, Profile.class);
		// System.out.println(response.getBody());
		userProfile = response.getBody();
		return new ModelAndView("home", "message", userProfile);
	}

	@RequestMapping("/profile")
	public ModelAndView userProfilePage() {
		return new ModelAndView("Profile", "message", userProfile);
	}

	@RequestMapping(value = "updateProfile")
    public ModelAndView viewingUserProfile(@ModelAttribute Profile profile) {        
        return new ModelAndView("UpdateDetails","profile",userProfile);
    }
 
 
    @RequestMapping("/update")
    public ModelAndView update(@ModelAttribute Profile profile) {
        restTemplate.put("http://localhost:2013/profiles", profile);
        return new ModelAndView("UpdateDetails", "message", "success");
 
    }

	/*
	 * @RequestMapping(value = "friendsList") public ModelAndView
	 * viewingFriendsProfile() { List<Integer> getFriendsList =
	 * userProfile.getPendingFriendList(); System.out.println(friendsList); for
	 * (Integer profileId : getFriendsList) { ResponseEntity<Profile> friendProfile
	 * = restTemplate.getForEntity("http://localhost:2013/profiles/"+profileId,
	 * Profile.class); System.out.println(friendProfile.getBody());
	 * friendsList.add(friendProfile.getBody()); } return new
	 * ModelAndView("FriendList", "friendList",friendsList);
	 * 
	 * 
	 * }
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout(@ModelAttribute Profile profile) {
		return new ModelAndView("index", "message", "logout success");
	}
}
