package org.thornfish.androidlibrary.socket;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * @name TcpManager
 * @class describe
 * @anthor tank QQ:297890301
 * @time 2017/7/20 15:56
 * @change
 * @chang time
 * @class describe
 */

public class TcpManager {
    public static final int STATE_FROM_SERVER_OK = 0;
    public static final int STATE_FROM_SERVER_OVER = 2;
    public static final int STATE_FROM_SERVER_ERR = 404;
    private static String dsName = "192.168.0.2";
    private static int dstPort = 1234;
    private Socket socket;

    private static TcpManager instance;


    public static TcpManager getInstance() {
        if (instance == null) {
            synchronized (TcpManager.class) {
                if (instance == null) {
                    instance = new TcpManager();
                }
            }
        }
        return instance;
    }



    InputStream is;
    OutputStream os;

    /**
     * 连接
     *
     * @return
     */
    public boolean connect(final Handler handler) {

        if (socket == null || socket.isClosed()) {
            new Thread(new Runnable() {
                @Override
                public void run() {


                            try {
                        socket = new Socket();
                        SocketAddress socAddress = new InetSocketAddress(dsName, dstPort);
                        socket.connect(socAddress, 5000 );
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
//                        Log.e("RuntimeException", "连接错误: " + e.getMessage() );
                    } catch (IOException e) {
                                Message msg = Message.obtain();
                                msg.what = STATE_FROM_SERVER_ERR;
                                handler.sendMessage(msg);
                        Log.e("RuntimeException", "连接错误: " + e.getMessage());
//                        throw new RuntimeException("连接错误: " + e.getMessage());
                    }



                    try {
                        is = socket.getInputStream();
                        os = socket.getOutputStream();
                        int length;
                        byte[] inputByte = new byte[1024];
                        byte[] outBuf = new byte[128];

                        while ( (length = is.read(inputByte, 0, inputByte.length)) > 0) {
                            final String result = new String(inputByte, 0, length);
//                            os.write(outBuf);
//                            os.flush();

                            if (length == 1) {
                                    int value = ((inputByte[0] & 0xFF));
                                    switch (value){
                                        case 2://游戏一轮结束
                                            Message msg = Message.obtain();
                                            msg.what = STATE_FROM_SERVER_OVER;
                                            handler.sendMessage(msg);
                                            break;
                                        case 3://抓到娃娃

                                            break;
                                    }
                                    Log.e("TcpManager", "len:" + "------" + length + "---" + value + "-----");
                                }
                                Message msg = Message.obtain();
                                msg.obj = result;
                                msg.what = STATE_FROM_SERVER_OK;
                                handler.sendMessage(msg);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is.close();
                            os.close();
                            socket.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                        }
                    }


//                    try {
//                        // 输入流，为了获取客户端发送的数据
//                        if (socket!=null) {
//                            InputStream ist = socket.getInputStream();
//                            byte[] buffer = new byte[1024];
//                            int len = -1;
//                            while ((len = ist.read(buffer)) != -1) {
//                                final String result = new String(buffer, 0, len);
//                                if (len == 1) {
//                                    int value = ((buffer[0] & 0xFF));
//                                    Log.e("TcpManager", "len:" + "------" + len + "---" + value + "-----");
//                                }
//
//                                Message msg = Message.obtain();
//                                msg.obj = result;
//                                msg.what = STATE_FROM_SERVER_OK;
//                                handler.sendMessage(msg);
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Log.e("RuntimeException", "获取错误: " + e.getMessage() );
//                    }

                }
            }).start();
        }

        return true;
    }


    /**
     * 发送信息
     *
     * @param content
     */
    public void sendMessage(byte[] content) {
        OutputStream os = null;
        try {
            if (socket != null&&socket.isConnected()) {
                os = socket.getOutputStream();
                os.write(content);
                os.flush();
            }
        } catch (IOException e) {
            Log.e("TcpManager", "发送失败:" + e.getMessage());
//            throw new RuntimeException("发送失败:" + e.getMessage());
        }
        //此处不能关闭
//      finally {
//          if (os != null) {
//              try {
//                  os.close();
//              } catch (IOException e) {
//                  throw new RuntimeException("未正常关闭输出流:" + e.getMessage());
//              }
//          }
//      }
    }

    /**
     * 关闭连接
     */
    public void disConnect() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                Log.e("RuntimeException", "关闭异常:" + e.getMessage());
//                throw new RuntimeException("关闭异常:" + e.getMessage());
            }
            socket = null;
        }
    }

    public Boolean iscontent(){
        return socket.isConnected();
    }

    public Boolean isclose(){

        return socket.isClosed();
    }

    public void close(){
        try {
            if (socket.isConnected())
            socket.close();
            socket.shutdownInput();
            socket.shutdownOutput();
            Log.e("TcpManager", "socket.isConnected():" + socket.isConnected());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
