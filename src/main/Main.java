package main;

import java.util.Scanner;

import main.components.Reseau;
import main.menus.Menu3;

public class Main {
    public static void main(String[] args){
        Reseau reseau = new Reseau();
        Scanner sc = new Scanner(System.in);
        Menu3.menu3(reseau, sc);
    }
}
