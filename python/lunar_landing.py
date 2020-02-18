#Ziyu Zhao
#71435169

#Resourses used:
##https://stackoverflow.com/questions/319426/how-do-i-do-a-case-insensitive-string-comparison
##http://www.runoob.com/python3/python3-loop.html
##https://www.runoob.com/python3/python3-check-is-number.html
##https://www.runoob.com/python/python-exceptions.html
##TA Chuanrui Zhu suggested me to use try.except to examine user input
###and helped me to fix some problems in the loop

#inputs that leads to a win: 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 50
#inputs that leads to a loss: 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0


#create an outer loop to repeat the game overtimes until break
play = 1
while play == 1:

    #define initial variables
    a = 100.0
    v = 0.0
    f = 100

    #user interaction
    print("Hi Captain! Welcome to Lunar Landing!")
    print("Current altitude = ", a)
    print("Current velocity = ", v)
    print("Current fuel = ", f)

    print("\n")

    #user input the amount of fuel to burn
    f_inp = str(input("How many liters of fuel do you want to burn in the next second?"))



    #just in case the user input amount is non-number
    #define a function when input is not an integer return false
    def input_valid(f_inp):
        try:
            float(f_inp)
            return True
        except ValueError:
            pass

        return False
    
    #ask for another numerical input
    while input_valid(f_inp) == False:
        f_inp = str(input("Please enter a number."))

    #turn f_inp from a string to a float (in case the player input decimals) for future calculation
    f_inp = float(f_inp)


    #just in case the user choose to burn negative amount of fuel
    #and in case the user asks to burn more fuel than he/she has
    if f_inp < 0:
        f_inp = 0
    elif f_inp > f:
        f_inp = f

    #to count time
    t = 1
    print(t, " seconds")
    
    #calculate velocity under increase and decrease influences
    v_new = round(v + 1.6 - f_inp*0.15, 2)
    v = v_new
    print("Current velocity = ", v)

    #calculate altitude after flying at given velocity for one second
    a_new = a-v
    a = a_new
    print("Current altitude = ", a)

    #calculate the amount of fuel left
    f_left = f - f_inp
    f = f_left
    print("Fuel left = ", f)

    #add the condition that when altitude < 0, the landing process is finished
    #until then the player has to keep input the amount of fuel to burn for every second
    while a > 0:

        #increase time
        t += 1
        print(t, " seconds")
        
        #player's input for the next second
        f_inp = str(input("How much fuel to burn in the next second?"))


        #repeat invalidity check
        def input_valid(f_inp):
            try:
                float(f_inp)
                return True
            except ValueError:
                pass

            return False

        while input_valid(f_inp) == False:
            f_inp = str(input("Please enter a number."))

        f_inp = float(f_inp)


        #repeat < 0, > 100 check
        if f_inp < 0:
            f_inp = 0
        elif f_inp > f:
            f_inp = f
            
        #if the player run out of 100 liters of fuel, treat fuel input as 0
        if f <= 0:
            f_inp = 0
            f = 0

        #calculate fuel burning cause a decrease to the velocity
        v_decrease = f_inp*0.15
        print("v_decrease = ", v_decrease)

        #increase velocity per second
        v += 1.6
        v -= v_decrease

        #calculate altitude after one second's flight
        a -= v
        f -= f_inp
        print("Current velocity = ", round(v, 2))
        print("Current altitude = ", round(a, 2))
        print("Fuel left = ", round(f, 2))


    #check and let the player know if he/she landed safely or not
    if v < 10:
        safe_str = "Your landed safely, congratulations!" +"\n"

    
    else:
        safe_str = "Crashing alert! You are in danger." +"\n"
    
    #give a statement of the velocity of the spaceship when it landed
    v_str = "Your velocity was {} meters/second.".format(round(v))+"\n"

    #give a statement of the amount of time used for the landing
    t_str = "Your landing took {} seconds.".format(t)+"\n"

    #how many fuel left
    f_str = "You have {} liters of fuel left.".format(round(f))+"\n"

    #show the player the game result, final velocity, and time used
    print(safe_str, v_str, t_str, f_str)

    #ask if the player wants to have another go
    play_again = str(input("Wanna play again? (y/n)"))
    #name the 1 character of the player's input
    answer = play_again[0]

    #check if the player's input begins with y/Y/n/N
    #if not ask again
    while answer != 'y' and answer != 'Y' and answer != 'n' and answer != 'N':
        play_again = str(input("Wanna play again? (y/n)"))
        answer = play_again[0]

    #if answer starts with an n/N, end the program
    if answer == 'n' or answer =='N':
        play = 2
        print("See you till next time.")
        break

    #if answer begins with a y/Y, the game natually goes again in while play == 1 loop
