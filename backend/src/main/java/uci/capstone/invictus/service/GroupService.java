package uci.capstone.invictus.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uci.capstone.invictus.entity.Group;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.exception.GroupNotFoundException;
import uci.capstone.invictus.exception.NoDataFoundException;
import uci.capstone.invictus.exception.RepositoryException;
import uci.capstone.invictus.exception.UserNotFoundException;
import uci.capstone.invictus.repository.GroupRepository;
import uci.capstone.invictus.repository.UserRepository;
import uci.capstone.invictus.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public Group findByGroupName(String groupName){
        return groupRepository.findByGroupName(groupName).orElseThrow(()->new GroupNotFoundException("group name", groupName));
    }

    public List<Group> findAllGroups(){
        List<Group> groups = groupRepository.findAll();
        /*if(groups.isEmpty())
            throw new NoDataFoundException();
         */
        return groups;
    }

    public List<Group> findByGroupLanguage(String language) {
        List<Group> groups = groupRepository.findByLanguages("{" + language + "}");
        /*if (groups.isEmpty()) {
            throw new GroupNotFoundException("group language", language);
        }*/
        return groups;
    }

    public void save(Group group){
        try {
            groupRepository.save(getLocationCoordinates(group));
        }
        catch (Exception e){
            throw new RepositoryException(e.getLocalizedMessage());
        }
    }

    public List<Group> findMatchedGroups(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User Name", username));

        String languages = user.getLanguages().stream().collect(Collectors.joining(",", "{", "}"));

        List<Group> groups = groupRepository.findBestMatchedGroups(user.getTypeOfIllness(), languages, user.getLocation());

        /*if (groups.isEmpty())
            throw new GroupNotFoundException("No best recommended groups available for the user", user.getFirstName());
*/
        return groups;
    }

    public List<Group> findByTypeOfIllnessAndLocation(String illness, String location){
        List<Group> groups = groupRepository.findByTypeOfIllnessAndLocation(illness, location);

        /*if (groups.isEmpty()) {
            throw new GroupNotFoundException("Illness:" + illness + "and", "Location:" + location);
        }*/
        return groups;
    }

    public List<Group> findByIllness(String illness){
        List<Group> groups = groupRepository.findByTypeOfIllness(illness);
        /*if (groups.isEmpty()) {
            throw new GroupNotFoundException("Illness", illness);
        }*/
        return groups;
    }

    public List<Group> findByLocation(String location){
        List<Group> groups = groupRepository.findByLocation(location);
        /*if (groups.isEmpty()) {
            throw new GroupNotFoundException("Location", location);
        }*/
        return groups;
    }

    public List<Group> findByIllnessAndLocation(String illness, String location){
        List<Group> groups = groupRepository.findByTypeOfIllnessAndLocation(illness, location);
       /* if (groups.isEmpty()) {
            throw new GroupNotFoundException("Illness-" + illness, "Location-" + location);
        }*/
        return groups;
    }

    public List<Group> findByIllnessAndLanguage(String illness, String language){
        List<Group> groups = groupRepository.findByIllnessAndLanguage(illness, "{" + language + "}");

        /*if (groups.isEmpty()) {
            throw new GroupNotFoundException("Illness-" + illness , "Language-" + "{" + language + "}");
        }*/
        return groups;
    }

    public HashMap<String, Integer> findIllnessBasedCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        groupRepository.findTotalNumberOfGroupsByIllness()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
    }

    public HashMap<String, Integer> findLocationBasedCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        groupRepository.findTotalNumberOfGroupsByLocation()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
    }

    public HashMap<String, Integer> findLanguageBasedCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        groupRepository.findTotalNumberOfGroupsByLanguage()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
    }

    public void update(Group newGroup) {

        groupRepository.findByGroupName(newGroup.getGroupName())
                .map(group -> {
                    group.setLanguages(newGroup.getLanguages());
                    group.setLocation(newGroup.getLocation());
                    group.setTypeOfIllness(newGroup.getTypeOfIllness());
                    group.setResources(newGroup.getResources());

                    return groupRepository.save(getLocationCoordinates(group));
                })
                .orElseGet(() -> {
                    return groupRepository.save(getLocationCoordinates(newGroup));
                });
    }

    private Group getLocationCoordinates(Group group){

        URL url = null;
        StringBuffer content = null;


        String key = System.getenv("GOOGLE_API_KEY");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("address", group.getLocation());
        parameters.put("key", key);

        try {
            url = new URL("https://maps.googleapis.com/maps/api/geocode/json?" + Constants.getParamsString(parameters));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();



            con.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


        JSONParser parse = new JSONParser();

        try {
            JSONObject obj = (JSONObject)parse.parse(content.toString());
            JSONArray results = (JSONArray) obj.get("results");

            JSONObject result = (JSONObject) results.get(0);

            JSONObject geometry = (JSONObject) result.get("geometry");
            JSONObject coordinates = (JSONObject) geometry.get("location");

            double lngvalue = (double) coordinates.get("lng");

            double latvalue = (double) coordinates.get("lat");

            group.setLat(latvalue);
            group.setLng(lngvalue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return group;
    }
}
