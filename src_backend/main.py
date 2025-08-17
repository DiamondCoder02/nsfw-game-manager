from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from platform import system
import os

import integration_check._values as _values # Init the value file so I can use it as global temporary storage
_values.init()

# https://docs.python.org/3/library/platform.html#platform.system
match system():
    case "Windows":
        _values.dirMainStorage = (os.getenv("APPDATA")or"") + "/DiamondCoder/nsfwGameManager"
        _values.dirSteamDefault =  (os.getenv("ProgramFiles(x86)")or"") + "/Steam/steamapps"
    case "Linux":
        _values.dirMainStorage = (os.getenv("HOME")or"") + "/DiamondCoder/nsfwGameManager"
        _values.dirSteamDefault = (os.getenv("HOME")or"") + "/.steam/steam/steamapps"
    case default:
        print("ERROR, unable to detect OS")
        # https://stackoverflow.com/questions/3365673/how-to-throw-an-error-window-in-python-in-windows
        from ctypes import windll  # An included library with Python install.   
        windll.user32.MessageBoxW(0, "Unable to detect your OS.", "ERROR", 0)
        quit()

import integration_check.folderCheckDatabase as folderCheckDB

folderCheckDB.check()

print(_values.dirMainStorage)
print(_values.dirSteamDefault)


# fastapi dev ./src_backend/main.py
app = FastAPI()