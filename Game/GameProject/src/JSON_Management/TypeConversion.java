package JSON_Management;

import Objects.Brick;
import Objects.GameDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class TypeConversion {
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
    public static JSONObject stringToJSONObject(String jsonString) throws ParseException {
        JSONParser jsonParser = new JSONParser();

        return (JSONObject) jsonParser.parse(jsonString);
    }
}

