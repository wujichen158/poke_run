package com.qdu.pokerun.actor.trade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.qdu.pokerun.lib.LibMisc;

public class AnimFun {
    public static boolean move(Actor item,float x,float y,float xv,float yv){
        boolean a=moveY(item,y,yv);
        boolean b=moveX(item,x,xv);
        return a && b;
    }

    public static boolean moveY(Actor item,float y,float yv){
        if(item.getY()>y&&item.getY()-y>=yv)
            item.setY(item.getY()-yv);
        else if(item.getY()<y&&y-item.getY()>=yv)
            item.setY(item.getY()+yv);
        else return true;
        return false;
    }

    public static boolean moveX(Actor item,float x,float xv){
        if(item.getX()>x&&item.getX()-x>=xv)
            item.setX(item.getX()-xv);
        else if(item.getX()<x&&x-item.getX()>=xv)
            item.setX(item.getX()+xv);
        else return true;
        return false;
    }

    public static boolean moveYOut(Actor item,float v){
        return moveY(item,0-item.getHeight(),v);
    }
    public static boolean moveXOut(Actor item,float v){ return moveX(item, LibMisc.SCREEN_W +item.getWidth(),v);}

    public static boolean setSize(Actor item,float w,float h,float wv,float hv){
        if(item.getWidth()>w&&item.getWidth()-w>-wv) {
            item.setHeight(item.getHeight()-hv);
            item.setWidth(item.getWidth() - wv);
        }else if(item.getWidth()<w&&w-item.getWidth()>=wv) {
            item.setHeight(item.getHeight()+hv);
            item.setWidth(item.getWidth() + wv);
        }else return true;
        return false;
    }

    public static boolean setHeight(Actor item,float h,float hv){
        if(item.getHeight()>h&&item.getHeight()-h>-hv) {
            item.setHeight(item.getHeight()-hv);
        }else if(item.getHeight()<h&&h-item.getHeight()>=hv) {
            item.setHeight(item.getHeight()+hv);
        }else return true;
        return false;
    }

    public static boolean fadeOut(Actor item,float a){
        if(item.getColor().a>a){
            item.getColor().a-=0.1;
        }else if(item.getColor().a<a){
            item.getColor().a+=0.1;
        }else
            return true;
        return false;
    }
}
