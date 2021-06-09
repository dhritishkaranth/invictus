package uci.capstone.invictus.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uci.capstone.invictus.entity.Pair;
import uci.capstone.invictus.entity.User;
import uci.capstone.invictus.exception.RepositoryException;
import uci.capstone.invictus.exception.UserNotFoundException;
import uci.capstone.invictus.repository.UserRepository;
import uci.capstone.invictus.utils.Constants;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers() {

        List<User> users = repository.findAll();
        /*if(users.isEmpty())
            throw new NoDataFoundException();*/
        return users;
    }

    public void createUser(User user){
        try {
            repository.save(getLocationCoordinates(user));
        }
        catch(Exception e){
            throw new RepositoryException(e.getMessage());
        }
    }

    public List<User> findUsersByFirstName(String firstName){

        List<User> users = repository.findByFirstName(firstName);
        /*if(users.isEmpty())
            throw new UserNotFoundException("First Name", firstName);*/
        return users;
    }

    public List<User> findUsersBySecondName(String secondName){

        List<User> users = repository.findBySecondName(secondName);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Second Name", secondName);*/
        return users;
    }

    public List<User> findUsersByLocation(String location){

        List<User> users = repository.findByLocation(location);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Location", location);*/
        return users;
    }

    public List<User> findUsersByTypeOfSeeker(Constants.Seeker seeker){

        List<User> users = repository.findByTypeOfSeeker(seeker);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Type Of Seeker", seeker.toString());*/
        return users;
    }

    public List<User> findUsersByLanguage(String language){

        List<User> users = repository.findByLanguages("{" + language + "}");
        /*if(users.isEmpty())
            throw new UserNotFoundException("Languages", language);*/
        return users;
    }

    public List<User> findUsersByTypeOfIllness(String illness){

        List<User> users = repository.findByTypeOfIllness(illness);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Type of Illness", illness);*/
        return users;
    }

    public List<User> findUsersByAnonymity(boolean flag){

        List<User> users = repository.findByAnonymous(flag);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Anonymous", String.valueOf(flag));*/
        return users;
    }

    public List<User> findUsersByAge(int age){

        List<User> users = repository.findByAge(age);
       /* if(users.isEmpty())
            throw new UserNotFoundException("Age", String.valueOf(age));*/
        return users;
    }

    public List<User> findUsersByGender(Constants.Gender gender){

        List<User> users = repository.findByGender(gender);
       /* if(users.isEmpty())
            throw new UserNotFoundException("Gender", gender.toString());*/
        return users;
    }

    public HashMap<String, Integer> findSeekerBasedUserCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        repository.findTotalOfUsersBySeeker()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
    }

    public List<User> findByIllnessAndLanguagesAndSeeker(String illness, String language, String seeker){
        List<User> users = repository.findByIllnessAndLanguageAndTypeOfSeeker(illness, "{" + language + "}", seeker);
        /*if(users.isEmpty())
            throw new UserNotFoundException("Illness-" + illness , "Language-" + language + "Seeker-" + seeker);
        */
        return users;
    }

    public List<User> findByIllnessAndSeeker(String illness, String seeker){

        List<User> users = repository.findByTypeOfIllnessAndTypeOfSeeker(illness, Constants.Seeker.valueOf(seeker));
        /*if(users.isEmpty())
            throw new UserNotFoundException("Illness-" + illness , "Seeker-" + seeker);*/
        return users;
    }

    public List<User> findByIllnessAndLanguage(String illness, String language){

        List<User> users = repository.findByTypeOfIllnessAndLanguages(illness, "{"+language+"}");
        /*if(users.isEmpty())
            throw new UserNotFoundException("Illness-" + illness , "Seeker-" + seeker);*/
        return users;
    }


    public List<User> findBySeekerAndLanguage(String seeker, String language){

        List<User> users = repository.findBySeekerAndLanguages(seeker, "{" + language + "}");
        /*if(users.isEmpty())
            throw new UserNotFoundException("Illness-" + illness , "Seeker-" + seeker);*/
        return users;
    }

    public User findByUsername(String username){
        Optional<User> user;
        try {
            user = repository.findByUsername(username);
        }
        catch(Exception e){
            throw new RepositoryException(e.getMessage());
        }

        return user.orElseThrow(() -> new UserNotFoundException("username", username));
    }

    public HashMap<String, Integer> findIllnessBasedCounts(){

        HashMap<String, Integer> map = new HashMap<>();
        repository.findTotalOfUsersByIllness()
                .forEach(pair -> map.put(pair.getKey(), pair.getValue()));
        return map;
    }

    public void update(User newUser){

        repository.findByUsername(newUser.getUsername())
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setSecondName(newUser.getSecondName());
                    user.setAnonymous(newUser.isAnonymous());
                    user.setTypeOfSeeker(newUser.getTypeOfSeeker());
                    user.setLanguages(newUser.getLanguages());
                    user.setLocation(newUser.getLocation());
                    user.setTypeOfIllness(newUser.getTypeOfIllness());
                    return repository.save(getLocationCoordinates(user));
                })
                .orElseGet(() -> {
                    return repository.save(getLocationCoordinates(newUser));
                });
    }


    private User getLocationCoordinates(User user){

        URL url = null;
        StringBuffer content = null;


        String key = System.getenv("GOOGLE_API_KEY");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("address", user.getLocation());
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

            user.setLat(latvalue);
            user.setLng(lngvalue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user;
    }

}
