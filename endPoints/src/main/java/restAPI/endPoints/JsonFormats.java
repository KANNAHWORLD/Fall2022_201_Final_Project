package restAPI.endPoints;

import java.util.ArrayList;

public class JsonFormats {
	//HEHE I HATE JAVA
}


class CreateProfileJson extends JsonFormats
{
	String first;
	String last;
	int age;
	int SexOrient;
	String insta;
	String description;
	Preferences preferred;
	Rank selfRank;
	Rank preferRanks;
}

class Preferences
{
	ArrayList<String> people;
}

class Rank
{
	int introverted;
	int humor;
	int adventure;
	int ambition;
	int artistic;
	int wOfAff;
	int physTouch;
	int gifts;
	int qualTime;
	int service;
}
