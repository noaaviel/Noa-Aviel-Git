using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;
using System.Timers;

public class Gui
{
    public static TcpListener serverSocket = new TcpListener(8888);
    public static int requestCount = 0;
    public static TcpClient clientSocket = default(TcpClient);
    public static NetworkStream netWorkStream;
    public bool activeSocket = true;
    public static string res = "";
    public ClassWave.Form1 form1;
   
    public void socket()
    { 
        serverSocket.Start();
        Console.WriteLine(" >> Server Status");
        clientSocket = serverSocket.AcceptTcpClient();
        Console.WriteLine(" >> Accept Connection From Client");
        while (activeSocket)
        {
            try
            { 
                netWorkStream = clientSocket.GetStream();
                byte[] bytesFrom = new byte[1024];
                netWorkStream.Read(bytesFrom, 0, 1024);
                String dataFromClient = System.Text.Encoding.ASCII.GetString(bytesFrom);
             
                if (dataFromClient == "")
                    continue;
              /*  if(dataFromClient == null)
                {
                    break;
                }*/
                dataFromClient = dataFromClient.Substring(0, dataFromClient.IndexOf("$"));
                Console.WriteLine(" >> Data From Client -" + dataFromClient);
               // Console.WriteLine(" >> End Of Data From Client >>\n");
                res = dataFromClient;
                ClassWave.Form1.DoChangeTextBox(res);

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
        }
    }

    public void sendTClient(string msg)
    {
        Byte[] sendBytes = Encoding.ASCII.GetBytes(msg);
        netWorkStream.Write(sendBytes, 0, sendBytes.Length);
        netWorkStream.Flush();
    }

    public void closeSocket()
    {
        activeSocket = false;
        clientSocket.Close();
        serverSocket.Stop();
        Console.WriteLine(" >> EXIT");
        Console.ReadLine();
    }

    
}
