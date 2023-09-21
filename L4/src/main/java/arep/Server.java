package arep;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import arep.Cache.MovieCache;
import arep.Service.WebService;
import arep.spring.ComponetLoader;
import arep.spring.Spring;

public class Server {
    public static void main(String[] args) throws Exception {
        Spring.run();
    }
}
