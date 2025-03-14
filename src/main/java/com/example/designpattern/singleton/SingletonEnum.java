package com.example.designpattern.singleton;

/**
 * @Author: ranz
 * @Since: 2025/2/18
 */
public class SingletonEnum {
    SingletonEnum(){

    }

    private enum InnerEnum{
        INSTANCE;
        SingletonEnum singletonEnum;
        InnerEnum(){
            singletonEnum = new SingletonEnum();
        }

    }

    public static SingletonEnum getInstance(){
        return InnerEnum.INSTANCE.singletonEnum;
    }

    public static void main(String[] args) {
        SingletonEnum singletonEnum = getInstance();
        for(int i=0;i<10;++i){
            System.out.println(singletonEnum == getInstance());
        }
    }

}
