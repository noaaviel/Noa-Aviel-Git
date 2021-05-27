
using System;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using System.Threading;


namespace ClassWave
{
    public partial class Form1 : Form
    {
        private Gui socket;
        private int timeTicks = 0;
        private static Form1 form = null;
        private delegate void EnableDelegate(string text);
        private SynchronizationContext mainThread;

        public Form1()
        {
            InitializeComponent();
            form = this;
            mainThread = SynchronizationContext.Current;
            socket = new Gui();
        }

        // Static method, call the non-static version if the form exist.
        public static void DoChangeTextBox(string text)
        {
            if (form != null)
                form.ChangeTextBox(text);
        }
        private void ChangeTextBox(string text)
        {
            // If this returns true, it means it was called from an external thread.
            if (InvokeRequired)
            {
                // Create a delegate of this method and let the form run it.
                textBox1.Invoke(new Action(() =>
                {
                    textBox1.Text = text;
                    performAction(text);
                }));
                return; // Important
            }
           
        }



        private void panel1_Paint(object sender, PaintEventArgs e)
        {

        }

        private void run_cmd(string cmd, string args)
        {
            ProcessStartInfo start = new ProcessStartInfo();
            start.FileName = "C:/Users/barm4/source/repos/ClassWave/main.exe";
            start.Arguments = string.Format("{0} {1}", cmd, args);
            start.UseShellExecute = false;
            start.RedirectStandardOutput = true;
            using (Process process = Process.Start(start))
            {
                using (StreamReader reader = process.StandardOutput)
                {
                    string result = reader.ReadToEnd();
                    Console.Write(result);
                }
            }
        }
        private void button1_Click(object sender, EventArgs e)
        {
            socket.form1 = this;
            Thread t = new Thread(new ThreadStart(socket.socket));
            t.Start();
        }

        public void performAction(string action)
        {
            switch (textBox1.Text)
            {
                case "left":
                    button4_Click(this, new EventArgs());
                    break;
                case "right":
                    button3_Click(this, new EventArgs());
                    break;
                case "center":
                    button5_Click(this, new EventArgs());
                    break;
                case "capture":
                    button6_Click(this, new EventArgs());
                    break;
                default:
                    break;
            }
        }

        static void ExecuteCommand(string command)
        {
            var processInfo = new ProcessStartInfo("cmd.exe", "/c " + command);
            processInfo.CreateNoWindow = true;
            processInfo.UseShellExecute = false;
            processInfo.RedirectStandardError = true;
            processInfo.RedirectStandardOutput = true;

            var process = Process.Start(processInfo);

            process.OutputDataReceived += (object sender, DataReceivedEventArgs e) =>
                Console.WriteLine("output>>" + e.Data);
            process.BeginOutputReadLine();

            process.ErrorDataReceived += (object sender, DataReceivedEventArgs e) =>
                Console.WriteLine("error>>" + e.Data);
            process.BeginErrorReadLine();

            process.WaitForExit();

            Console.WriteLine("ExitCode: {0}", process.ExitCode);
            process.Close();
        }

        public static string RunFromCmd(string rCodeFilePath, string args)
        {
            string file = rCodeFilePath;
            string result = string.Empty;

            try
            {

                var info = new ProcessStartInfo(@"C:\Users\barm4\source\repos\ClassWave\main.exe");
                info.Arguments = rCodeFilePath + " " + args;

                info.RedirectStandardInput = false;
                info.RedirectStandardOutput = true;
                info.UseShellExecute = false;
                info.CreateNoWindow = true;

                using (var proc = new Process())
                {
                    proc.StartInfo = info;
                    proc.Start();
                    proc.WaitForExit();
                    if (proc.ExitCode == 0)
                    {
                        result = proc.StandardOutput.ReadToEnd();
                    }
                }
                return result;
            }
            catch (Exception ex)
            {
                throw new Exception("R Script failed: " + result, ex);
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            socket.closeSocket();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            panel1.BackColor = Color.Gray;
            label3.Visible = true;
            label3.Text = "Camera moving left";
        }

        private void button7_Click(object sender, EventArgs e)
        {
            this.timeTicks = ((int)numericUpDown1.Value) * 600;
            timer1.Enabled = true;
            timer1.Start();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            this.timeTicks--;
            if (this.timeTicks <= 0)
            {
                timer1.Stop();
                label2.Text = "TIME UP";

            }
            if (((this.timeTicks%600) / 10)<10)
                label2.Text = "time remaining: " + this.timeTicks / 600+ ":0"+((this.timeTicks%600)/10);
            else
                label2.Text = "time remaining: " + this.timeTicks / 600 + ":" + ((this.timeTicks % 600) / 10);

        }


        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button5_Click(object sender, EventArgs e)
        {
            panel1.BackColor = Color.Gray;
            label3.Visible = true;
            label3.Text = "Camera moving to the center";
        }

        
        private void button8_Click(object sender, EventArgs e)
        {

        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
             
        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            panel1.BackColor = Color.Gray;
            label3.Visible = true;
            label3.Text = "Camera moving to the right";
        }

        private void button6_Click(object sender, EventArgs e)
        {
            label3.Visible = true;
            panel1.BackColor = Color.Gray;
            label3.Text = "Screenshot taken";
        }

        private void timer2_Tick(object sender, EventArgs e)
        {

        }
    }
}
