package com.qdu.pokerun.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.utils.Logger;

import java.util.concurrent.TimeUnit;

public class PokeRunServer extends ApplicationAdapter {

    public static final int TCP_PORT = 9000;

    ServerSocket server= null;
    Socket socket = null;

    @Override
    public void create() {
        System.out.println("Initializing Finisterra Server...");
        long start = System.currentTimeMillis();

        this.server = Gdx.net.newServerSocket(Net.Protocol.TCP, TCP_PORT, null);

//        System.out.println("ip:"+socket.getRemoteAddress());
        System.out.println("Elapsed time: " + TimeUnit.MILLISECONDS.toSeconds(Math.abs(start - System.currentTimeMillis())) + " seconds.");
        System.out.println("PokeRun server loaded");
    }

    @Override
    public void render() {
        this.socket = this.server.accept(null);
    }

    @Override
    public void dispose() {
        this.socket.dispose();
        this.server.dispose();
        System.exit(0);
    }
}
