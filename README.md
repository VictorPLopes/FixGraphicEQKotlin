# FixGraphicEQ
### This is a Kotlin remake of [my previous project](https://github.com/VictorPLopes/FixGraphicEQ). Both do the exact same thing and have the exact same features. To the user, there should be no difference between both. I made this in Kotlin to try the language, and translating an existing project seemed like a good way to start learning it, which is why I made this version.
Recently, I was trying to use [HeSuVi](https://sourceforge.net/projects/hesuvi/) for the first time to add surround virtualization to my headset, and equalize it. Since my model wasn't on the [AutoEQ.app database](https://github.com/jaakkopasanen/AutoEq/tree/master/results), I had to do it myself. So I got a frequency response curve of my headset and tried to use it on [AutoEQ.app](https://autoeq.app), setting the target to a curve of my choice. The graphics looked fine on the website so I tried to export the equalizer settings to the Equalizer APO GraphicEq format to use on HeSuVi.
<br><br>
**The issue is that the equalizer exported from AutoEQ.app was shifted down in HeSuVi**, maybe it was a mistake on my part, but I couldn't find a way to fix it (I'm new to this stuff). **So I decided to make a script to offset the equalizer to where it should be.**
<br><br>
Since I've been wanting to test Rider for a while, and make the switch from Visual Studio, I decided to quickly throw together a project to test it out and solve my problem at the same time. **Then I decided to translate it to Kotlin to learn more about the language, and this is the result.** The code is far from perfect, and I'm sure there are better ways to do it, but it works and it was just a quick project to test Rider. **Feel free to use it if you have the same problem as me** or improve it if you want to.

![image](https://github.com/VictorPLopes/FixGraphicEQKotlin/assets/77900343/449efbb4-bfe4-42c9-ba4a-5a348ef10ad6)

## How to use

1. Download the latest release from the [releases page](https://github.com/VictorPLopes/FixGraphicEQ/releases).
2. Run the executable.
3. Press ENTER and select the text file exported from AutoEQ.app in the Open File dialog.
4. Type the offset you want to apply to the equalizer. I don't know if there's a very precise way to find it, but you have to manually see what the difference between the AutoEQ.app curve and the curve in HeSuVi is. For example, in my case, the curve was shifted down by 12.2 dB, so I typed `12.2` (to shift it up 12.2 dB) and pressed ENTER.
5. The script will create a new file with the same name as the original file, but with `_offsetN` appended to it, where `N` is the offset you typed. For example, if you typed `12.2`, the new file will be named `your_file_offset12.2.txt`. It will be created in the same directory as the original file.
6. You can now use the new file in HeSuVi.

## How to build

Building the project is very simple, just clone the repository, open the solution and build it. I used JDK 20 for this and IntelliJ's default Kotlin settings.
