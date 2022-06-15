package JSON_Management;

import GameUtils.Brick;
import GameUtils.GameDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Naheem J.
 * Class with static methods in charge of doing all the conversions of data, for example JSON to gameDetails object.
 */
public class TypeConversion {

    /**
     * @author Naheem J.
     * Method used for converting a JSON object to a gameDetails object, which contains all of the important data regarding the game.
     * @param jsonObject the Json containing all the game data sent from the server
     * @return Returns the gameDetails object which was parsed from the initial json
     */
    public static GameDetails makeGameDetailsObject(JSONObject jsonObject) {
        Brick lifepoints = new Brick();
        Brick extraBall = new Brick();
        Brick doubleRacket = new Brick();
        Brick halfRacket = new Brick();
        Brick moreSpeed = new Brick();
        Brick lessSpeed = new Brick();
        GameDetails gameObject = new GameDetails(lifepoints, extraBall, doubleRacket, halfRacket, moreSpeed, lessSpeed);

        try {gameObject.setGreenValue(Integer.parseInt((jsonObject.get("greenValue").toString())));}
        catch (NullPointerException e) {gameObject.setGreenValue(0);}

        try {gameObject.setYellowValue(Integer.parseInt((jsonObject.get("yellowValue").toString())));}
        catch (NullPointerException e) {gameObject.setGreenValue(0);}

        try {gameObject.setOrangeValue(Integer.parseInt((jsonObject.get("orangeValue").toString())));}
        catch (NullPointerException e) {gameObject.setGreenValue(0);}

        try {gameObject.setRedValue(Integer.parseInt((jsonObject.get("redValue").toString())));}
        catch (NullPointerException e) {gameObject.setGreenValue(0);}

        makeBrickObject((JSONArray)jsonObject.get("lifePoints"), lifepoints);
        makeBrickObject((JSONArray)jsonObject.get("extraBall"), extraBall);
        makeBrickObject((JSONArray)jsonObject.get("doubleRacket"), doubleRacket);
        makeBrickObject((JSONArray)jsonObject.get("halfRacket"), halfRacket);
        makeBrickObject((JSONArray)jsonObject.get("moreSpeed"), moreSpeed);
        makeBrickObject((JSONArray)jsonObject.get("lessSpeed"), lessSpeed);

        return gameObject;
    }

    /**
     * @author Naheem J.
     * Void method used for parsing a json array containing the data of the bricks to a Brick object
     * @param jsonArray the initial json array
     * @param brickObject the Brick object used for parsing the data
     */
    public static void makeBrickObject(JSONArray jsonArray, Brick brickObject){
        int count = 0;
        int size;

        try{size = jsonArray.size();}
        catch (NullPointerException e){ size = 0;}

        while (count < size){
            if (count ==0){
                brickObject.setxValue(((Long)jsonArray.get(count)).intValue());
            }else {
                brickObject.setyValue(((Long)jsonArray.get(count)).intValue());
            }
            count++;
        }
    }

    /**
     * @author Naheem J.
     * Method used for parsing a string with the structure of a json which gets sent from the server, and then gets parsed into a Json object.
     * @param jsonString the initial json string
     * @return Returns the json object.
     * @throws ParseException Exception in case the parsing fails.
     */
    public static JSONObject stringToJSONObject(String jsonString) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

        return jsonObject;
    }
}

