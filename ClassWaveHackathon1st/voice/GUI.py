from tkinter import *
from tkinter.ttk import Combobox
import time
from playsound import playsound

window = Tk()

window.title("ClassWave")

window.geometry('450x450')
def clickRight():
   lbl = Label(window, text="camera turn right")
   lbl.place(x=340 , y=80)

def clickLeft():
   lbl = Label(window, text="camera turn left")
   lbl.place(x=10 , y=80)

def clickCenter():
   lbl = Label(window, text="camera center")
   lbl.place(x=185 , y=80)

btnRight = Button(window, text="right ", command=clickRight)
btnRight.grid(column=1, row=0)
btnRight.place(x=270 ,y=200)

btnLeft = Button(window, text="left  ", command=clickLeft)
btnLeft.grid(column=1, row=0)
btnLeft.place(x=150 ,y=200)

btnCenter = Button(window, text="center", command=clickCenter)
btnCenter.grid(column=1, row=0)
btnCenter.place(x=210 ,y=200)


def clickCaputre():
   lbl = Label(window, text="screen shot saved")
   lbl.place(x=176 , y=140)

btnCapture = Button(window, text="                  capture                  ", command=clickCaputre)
btnCapture.grid(column=1, row=0)
btnCapture.place(x=150 ,y=260)

lbl = Label(window, text="")
lbl.grid()
lbl.place(x=150 ,y=340)

def clicClock():
   def countdown(count):
       if (count%60 < 10):
           label['text'] = str((int)(count / 60)) + ":0" + str(count % 60)
       else:
           label['text'] = str((int)(count / 60)) + ":" + str(count % 60)
       label.place(x=170 ,y=370)
       if (count > 0):
        window.after(1000, countdown, count - 1)





   label = Label(window)
   label.place(x=35, y=15)
   countdown(int(combo.get())*60)



btnTimer = Button(window, text="                    timer                    ", command=clicClock)
btnTimer.grid(column=1, row=0)
btnTimer.place(x=150 ,y=300)



combo = Combobox(window)


combo['values']= (10, 15, 20, 25, 30 , 35 ,40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120)

combo.current(0)
combo.grid(column=0, row=0)
combo.place(x=160 ,y=330)


window.mainloop()