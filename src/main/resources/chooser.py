import os
import random
import subprocess

dir_path = os.path.dirname(os.path.realpath(__file__))

propertiesPath = dir_path + "\\settings.properties"
musicLibPath: str
musicPlayerPath: str

with open(propertiesPath) as f:
    content = f.readlines()
    content = [x.strip() for x in content]
    for line in content:
        splitted = line.split("=")
        propName = splitted[0]
        propValue = splitted[1]
        if propName == 'chooser.musicLibraryPath':
            musicLibPath = propValue
        elif propName == 'chooser.playerPath':
            musicPlayerPath = propValue

print("music library :", musicLibPath)
print("music player path :", musicPlayerPath)

albumsList = []

for subdir, dirs, files in os.walk(musicLibPath):
    for file in files:
        if file.endswith(("mp3",
                          "flac",
                          "mp4",
                          "m4p",
                          "wav",
                          "alac",
                          "wma",
                          "aac",
                          "m4a")):
            albumsList.append(subdir)
            break
for x in albumsList:
    print(x)
size = len(albumsList)
print("albums found :", size)
selectedAlbumIndex = random.randint(0, size)
print("selected album index :", selectedAlbumIndex)
selectedAlbumPath = albumsList[selectedAlbumIndex]
print("selected album :", selectedAlbumPath)

args = [musicPlayerPath, selectedAlbumPath]
subprocess.call(args)
