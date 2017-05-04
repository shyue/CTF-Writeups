**Problem:** My friend was holding his passcode checker tool, but he was too busy looking at his screen and he tripped! Now the passcode has fallen out of the code and out of his brain. See if you can recover it.

**File:** [brokenpasscode.apk](files/brokenpasscode.apk)

**Hint:** There are no repeats in the digits of the correct passcode. If you find something that has repeats, maybe that's becuase my friend dropped it... also he suddenly had an epiphany and remembered that he hates 0s, and his passcode doesn't have any...

**Solution:**

Before I go and solve the problem, I hope you all enjoyed solving it on angstromCTF :) It was my first time writing a problem, and I hope it was challenging and fun for all of you.

Now to begin:

So the first step I usually take when looking at an apk is to run it through a decompiler (I usually use jadx-gui).

Running it through jadx-gui, I look at the MainActivity.java and see that it's a fairly simple program - it looks like it takes in a string as input, compares it to a value from the meta-data, and then says that if you think the value is correct, enter it as the flag.
![alt tag](files/1.jpg "jadx-gui decompilation of MainActivity.java")





