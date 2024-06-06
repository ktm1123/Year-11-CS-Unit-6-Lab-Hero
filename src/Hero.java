import java.util.Random;

public class Hero {
    private String name;
    private int hitPoints;

    public Hero(String n){
        name = n;
        hitPoints = 100;
    }


    public String getName(){
        return name;
    }
    public int getHitPoints(){
        return hitPoints;
    }
    public void setHitPoints(int n){
        hitPoints=n;
    }

    public String toString(){
        return "Hero{name=" + "'"+name+"', hitPoints=" + hitPoints + "}";
    }
    public void attack(Hero opponent){
        int oppHP = opponent.getHitPoints();;
        Random random = new Random();
        double randn = random.nextDouble();
        if (randn >= 0.5){
            int newhp = hitPoints-=10;
            if (newhp>0) hitPoints=newhp;
            else hitPoints = 0;
        }
        else{
            opponent.setHitPoints(oppHP-10);
        }
    }

    public void senzuBean(){
        hitPoints=100;
    }
    private void fightUntilTheDeathHelper(Hero opponent){
        Hero hero = new Hero(name);
        while (hero.getHitPoints() != 0 && opponent.getHitPoints() != 0){
            attack(opponent);
            opponent.attack(hero);
        }
    }
    public String fightUntilTheDeath(Hero opponent){
        Hero hero = new Hero(name);
        hero.senzuBean();
        opponent.senzuBean();
        hero.fightUntilTheDeathHelper(opponent);
        String a = hero.getName()+": "+hero.getHitPoints();
        String b = opponent.getName()+": "+hero.getHitPoints();
        return a + "         " + b;
    }

    private int[] nFightsToTheDeathHelper(Hero opponent, int n){
        Hero hero = new Hero(name);
        int[] heroWins = new int[n];
        for (int x: heroWins) x=-1;
        for (int i=0; i<n; i++) {
            while (hero.getHitPoints() != 0 && opponent.getHitPoints() != 0) {
                hero.attack(opponent);
                opponent.attack(hero);
            }
            if (hero.getHitPoints() == 0) {
                hero.senzuBean();
            }
            if (opponent.getHitPoints() == 0){
                opponent.senzuBean();
                if (hero.getHitPoints()!=0) heroWins[i] = 1;
            }

        }
        return heroWins;
    }
    public String nFightsToTheDeath(Hero opponent, int n){
        Hero hero = new Hero(name);
        int[] heroWins = nFightsToTheDeathHelper(opponent,n);
        int heroWinCount = 0;
        for (int x: heroWins){
            if (x==1) heroWinCount++;
        }
        String a = hero.getName()+": "+heroWinCount+" wins";
        int oppWinCount = n - heroWinCount;
        String b = opponent.getName()+": " + oppWinCount +" wins";
        String c = "";
        if (heroWinCount>oppWinCount) c = hero.getName()+" wins!";
        else c = opponent.getName()+" wins!";
        return a + "\n" + b + "\n" + c;
    }

    public void dramaticFightToTheDeath(Hero opponent){
        Hero hero = new Hero(name);
        hero.senzuBean();
        opponent.senzuBean();
        while (hero.getHitPoints() > 0 && opponent.getHitPoints() > 0){
            attack(opponent);
            opponent.attack(hero);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
            System.out.println((opponent.getName() + ": " + opponent.getHitPoints() +" "+ hero.getName() + ": " + hero.getHitPoints()));
        }




    }



}