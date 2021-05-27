import playsound
import speech_recognition as sr
from gtts import gTTS
import os
import cv2
from PIL import Image
import pyautogui, time
import win32com.client as win32
image_counter = 0
import connection
import threading

def _window_movement_windows():
    PowerPoint = win32.DispatchEx("PowerPoint.Application")
    PowerPoint.Visible = True
    PowerPoint.Presentations("http.pptx").Windows(1).Activate()

def speak(text):
    tts = gTTS(text=text, lang='en')
    filename = "voice.mp3"
    tts.save(filename)
    playsound.playsound(filename)
    os.remove(filename)

def get_audio():
    r = sr.Recognizer()
    with sr.Microphone() as source:
        print('Please speak now')
        audio = r.listen(source, phrase_time_limit=3)
        said = ''
        try:
            said = r.recognize_google(audio)
        except sr.UnknownValueError:
            print("Exception, unable to understand what you said")
            said = 'error'
        except sr.RequestError:
            print("Exception Server down")
    return said


def respond(voice_data):
    global image_counter
    if voice_data is None:
        return
    if "left" in voice_data:
        speak("Moving camera to the left.")
        connection.send_msg("left")
    elif "right" in voice_data:
        speak("Moving camera to the right.")
        connection.send_msg("right")
    elif "capture" in voice_data:
        speak("getting image")
        cam = cv2.VideoCapture(0, cv2.CAP_DSHOW)
        frame = cam.read()[1]
        string_cap_name = 'img' + str(image_counter+1) + '.png'
        # cv2.imwrite('img2.png', frame)
        cv2.imwrite(string_cap_name, frame)
        image_counter = image_counter + 1
       # snap_shot()
    elif "center" in voice_data:
        speak("Moving camera to the center")
        connection.send_msg("center")
    elif "next" in voice_data:
      #  _window_movement_windows()
        pyautogui.keyDown("right")
        speak("next Slide")
        connection.send_msg("next")
    elif "previous" in voice_data:
    #    _window_movement_windows()
        pyautogui.keyDown("left")
        speak("previous slide")
        connection.send_msg("previous")
def get_command(voice_data):
    words = {
        'right': ['right', 'rice', 'wright', 'light'],
        'center': ['center', 'sensor', 'Center'],
        'left': ['left', 'lyft', 'list'],
        'next': ['next' , 'none' ],
        'previous': ['previous'],
        'capture': ['capture', 'Smith', 'captain', 'caption', 'capthca', 'capism']
    }
    for k, y in words.items():
        for v in y:
            if voice_data in v:
                return k

def make_pdf():
    global image_counter
    image_lst = []
    image1 = Image.open(r'img' + str(1) + '.png')
    im1 = image1.convert('RGB')
    for i in range(1,image_counter):
        image_lst.append(Image.open(r'img' + str(i+1) + '.png'))
        image_lst[i-1].convert('RGB')
    im1.save(r'file.pdf',  save_all=True,append_images=image_lst)


def get():
    global image_counter
    connect_thread = threading.Thread(target=connection.connect())
    while True:
        boss = {'boss': ['bus', 'but', 'both', 'bust','boats','box', 'boss','ross']}
        said = get_audio()
        if said == 'error':
            continue
        print(said)
        for values in boss.values():
            if said in values:
                speak("yes?")
                command = get_command(get_audio())
                print(command)
                respond(command)
        if image_counter == 2:
            make_pdf()
            break

get()
