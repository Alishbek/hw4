package com.alishbek;

import java.util.Random;

public class Main {
    public static int bossHealth=700;
    public static int bossDamage=50;

    public static int[] heroesHealth={270,280,260,300};
    public static int[] heroesDamage={20,15,25,0};

    public static String[] heroesAttackType={"Physical","Magical","Kinetic","Medic"};
    public static String bossDefenseType="";

    public static int roundNumber=0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()){
            round();
        }


    }
    public static Boolean isGameFinished(){
        if(bossHealth<=0){
            System.out.println("Heroes won");
            return true;
        }
        if (heroesHealth[0]<=0&&heroesHealth[1]<=0&&heroesHealth[2]<=0) {
            System.out.println("Boss won!");
            return true;
        }
        return false;
    }
    public static void printStatistics(){
        System.out.println(roundNumber+" ROUND!!!!!!");
        System.out.println("Boss health: "+bossHealth+"   ["+bossDamage+"]");
        for (int i = 0; i < heroesDamage.length; i++) {
            System.out.println(heroesAttackType[i]+" health: "+heroesHealth[i]+"  ["+heroesDamage[i]+"]");
        }

    }
    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i]<bossDamage) {
                heroesHealth[i] = 0;
            } else{
                heroesHealth[i]=heroesHealth[i]-bossDamage;
            }
        }
    }
    public static void heroesHits(){
        for (int i = 0; i <heroesDamage.length ; i++) {
            if(heroesHealth[i]>0&&bossHealth>0){
                if(bossDefenseType==heroesAttackType[i]){
                    Random r = new Random();
                    int coef= r.nextInt(6)+2;  //2.3.4.5.6.7.8

                   if (bossHealth<heroesDamage[i]*coef){
                       bossHealth=0;
                   } else{
                       bossHealth=bossHealth-heroesDamage[i]*coef;
                   }
                    System.out.println("Critiсal damage; "+heroesDamage[i]*coef);
                }else{
                    if (bossHealth < heroesDamage[i]) {
                        bossHealth=0;
                    }else{
                        bossHealth=bossHealth-heroesDamage[i];
                    }
                }
            }

        }
    }
    public static void heal(){
        Random random2 = new Random();
        int randomHeal= random2.nextInt(3);
        int healHero=heroesHealth[randomHeal];
        Random random3 = new Random();
        int healNumber=random3.nextInt(30)+20;
        for (int i = 0; i < heroesHealth.length-1; i++) {
            if (heroesHealth[i]<100&&heroesHealth[i]>0&&heroesHealth[3]>0&&heroesHealth[i]==healHero) {
                heroesHealth[i]=heroesHealth[i]+healNumber;
                System.out.println("Medic choose: "+heroesAttackType[randomHeal]+"    [+"+healNumber+"]");
            }
        }

        }
    public static void round(){
        roundNumber++;
        heal();
        chooseBossDefenseType();
        bossHits();
        heroesHits();

        printStatistics();
    }
    public static void chooseBossDefenseType(){
        Random random = new Random();
        int randomIndex=random.nextInt(3);
        bossDefenseType=heroesAttackType[randomIndex];
        System.out.println("Boss choose: "+bossDefenseType);
    }

}
