package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
public class Main {

    //////////

    public static boolean isProst(int e, int x) {
        if (x>e) return isProst(x,e);
        if ((x==0) && (e==1)) return true;
        if ((x==0) && (e!=1)) return false;
        return isProst(x, e%x);
    }

    public static int fastExp(int a, int z, int n ){
        int a1 = a, z1 = z, x = 1;
        while(z1!=0){
            while ((z1 % 2) == 0){
                z1/=2;
                a1 = (a1*a1) % n;
            }
            --z1;
            x = (x*a1) % n;
        }
        return x;
    }

    /////////////
    public static void shifrovkaRSA() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку:");
        String stroka = in.nextLine();
        int[] prostChisla = { 7, 11, 13, 17, 19, 23, 29, 31, 37};

        //   System.out.println("Введите первое простое число");
        //   int p = in.nextInt();
        //  System.out.println("Введите второе простое число");
        //   int q = in.nextInt();

        int randomIndex = new Random().nextInt(prostChisla.length);
        int p = prostChisla[randomIndex];
        int q = 0;
        if (randomIndex + 2 < prostChisla.length) {
            q = prostChisla[randomIndex + 2];
        } else {
            q = prostChisla[randomIndex - 2];
        }
        int r = p * q;
        System.out.println(p);
        System.out.println(q);
        System.out.println(r);
        int x = (p-1)*(q-1);
        boolean find = false;
        int e = 0;
       // System.out.println("Введите экспоненту");
       // int e = in.nextInt();
        while(!find) {
            e = new Random().nextInt(x - 2) + 2;
            if (isProst(e,x)){
                find = true;
            }
        }
        System.out.println(x);
        System.out.println(e);
        int x0 = 1, x1 = 0, y0 = 0, y1 = 1, d0 = x, d1 = e;
        while (d1>1){
            int g = d0/d1;
            int d2 = d0 % d1;
            int x2 = x0-(g*x1);
            int y2 = y0-g*y1;
            d0 = d1;
            d1 = d2;
            x0 = x1;
            x1 = x2;
            y0 = y1;
            y1 = y2;
        }
        if (y1<0){
            y1 +=x;
        }
        int d = y1;
        System.out.println(d);
        int[] strokaShifr = new int[stroka.length()];
        for (int i = 0; i<stroka.length();i++){
            strokaShifr[i] = stroka.charAt(i)-63;
        }
        System.out.println("Зашифрованная строка");
        for (int c : strokaShifr) {
            System.out.print(c);
            System.out.print(' ');
        }
        System.out.println();
        int[] key = {e,r};
        System.out.println("Образ");
        int h = new Random().nextInt(100)+50;
        int h0 = h;
        for (int i=0; i<strokaShifr.length;i++){
            h =(int) (Math.pow((h+strokaShifr[i]),2)  % r);
        }
        int s =(int) Math.pow(h,d) % r;
        System.out.println(h);


        deshifrRSA(strokaShifr, s,h, h0, key);
    }

    public static void deshifrRSA(int[] strokaShifr, int s, int h,int h0, int[] key) {
       int m =(int) Math.pow(s,key[0]) % key[1];
       //strokaShifr[3] = strokaShifr[3]+15;
        for (int i=0; i<strokaShifr.length;i++){
            h0 =(int) (Math.pow((h0+strokaShifr[i]),2)  % key[1]);
        }
        System.out.println("Контрольный образ");
        System.out.println(h0);
        if (h0 == h){
            System.out.println("Подпись корректна");
        }
        else{
            System.out.println("Подпись некорректна");
        }
    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        shifrovkaRSA();
    }
}
