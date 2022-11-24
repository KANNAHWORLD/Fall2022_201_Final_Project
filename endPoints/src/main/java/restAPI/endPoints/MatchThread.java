package restAPI.endPoints;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

public class MatchThread extends Thread {
	private int person;
    public static Vector<CreateProfileJson> peop;
    public static TreeMap<Integer, Vector<Vector<String>>> set;
    public static HashMap<String, HashMap<String, Integer>> rated;
    public MatchThread(int p){
        person = p;
    }
    

    public void run(){
        for (int i = person + 1; i < peop.size(); i++){
            int compatability = 0;
            if (peop.get(i).first == null && peop.get(person).first == null && peop.get(i).SexOrient == peop.get(person).gender && peop.get(person).SexOrient == peop.get(i).gender){
                if (rated.get(peop.get(person).UserName).get(peop.get(i).UserName) == null)
                {    
                    compatability += (10 - Math.abs(peop.get(i).preferRank.adventure - peop.get(person).selfRank.adventure));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.ambition - peop.get(person).selfRank.ambition));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.artistic - peop.get(person).selfRank.artistic));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.extroverted - peop.get(person).selfRank.extroverted));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.gifts - peop.get(person).selfRank.gifts));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.humor - peop.get(person).selfRank.humor));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.physTouch - peop.get(person).selfRank.physTouch));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.qualTime - peop.get(person).selfRank.qualTime));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.service - peop.get(person).selfRank.service));
                    compatability += (10 - Math.abs(peop.get(i).preferRank.wOfAff - peop.get(person).selfRank.wOfAff));
                }
                else{
                    compatability += (rated.get(peop.get(person).UserName).get(peop.get(i).UserName)*10);
                }
                if (rated.get(peop.get(i).UserName).get(peop.get(person).UserName) == null)
                {    
                    compatability += (10 - Math.abs(peop.get(person).preferRank.adventure - peop.get(i).selfRank.adventure));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.ambition - peop.get(i).selfRank.ambition));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.artistic - peop.get(i).selfRank.artistic));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.extroverted - peop.get(i).selfRank.extroverted));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.gifts - peop.get(i).selfRank.gifts));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.humor - peop.get(i).selfRank.humor));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.physTouch - peop.get(i).selfRank.physTouch));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.qualTime - peop.get(i).selfRank.qualTime));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.service - peop.get(i).selfRank.service));
                    compatability += (10 - Math.abs(peop.get(person).preferRank.wOfAff - peop.get(i).selfRank.wOfAff));
                }
                else{
                    compatability += (rated.get(peop.get(i).UserName).get(peop.get(person).UserName)*10);
                }
                insertTree(compatability, peop.get(i).UserName, peop.get(person).UserName);
            }
        }
    }
    
    
    private synchronized void insertTree(int comp, String p1, String p2){
        if (set.get(comp) != null){
            Vector<String> toadd = new Vector<String>();
            toadd.add(p1);
            toadd.add(p2);
            set.get(comp).add(toadd);
        }
        else{
            Vector<Vector<String>> vecadd = new Vector<Vector<String>>();
            Vector<String> toadd = new Vector<String>();
            toadd.add(p1);
            toadd.add(p2);
            vecadd.add(toadd);
            set.put(comp, vecadd);
        }
    }
}