#Ziyu Zhao
#71435169

#Resources used:
#https://stackoverflow.com/questions/4041238/why-use-def-main
#https://docs.python.org/3/library/random.html
#TA Chuanrui Zhu answered my questions on the use of def main()
##and on how to make computer_score maintain its previously assigned value 


#define the function that tells the player the rules of the game
def print_instructions():
    
    """Welcome to Pig!\nIn this game your will roll a six-sided die multiple times \
against Pig! Each turn you can roll the die as many times as you want (once at least), unless you \
choose to stop, or you roll a 6. If you roll a 6, your score for the turn will be \
0. If your add-up score of each turn reaches 50 first, you win; if not, Pig wins. \
Have fun!\n"""

print(print_instructions.__doc__)

import random
def roll():

    """Returns random number in the range of 1-6 to simulate dice rolling."""
    
    x = random.randint(1,6)
    return x

def print_line(char):

    """Draws cut-off lines to make the interface clearer-looking."""
    
    print(char * 30)

round_count = 1
def print_round_count():
    
    """Prints the count of rounds"""
    
    global round_count
    print("\n"+"        #Round",round_count,"#"+"\n")
    
#define the function that asks for and processes the player's instructions
def ask_yes_or_no(prompt):
    
    """Returns true if the player answers the prompt question with y or Y,\
and returns False if the player's answer starts with n or N. Ask the player\
to answer again if his/her response starts with other letters or numbers."""
    
    demand = str(input(prompt))
    while demand[0] != "y" and demand[0] != "Y" and demand[0] != "n" and\
          demand[0] != "N":
        demand = str(input("Please enter y/n"))

    if demand[0] == "y" or demand[0] == "Y":
        answer = True
    elif demand[0] == "n" or demand[0] == "N":
        answer = False

    return answer

#assign initial values to a few variables for later use
round_count = 1
computer_score = 0
human_score = 0

#define computer move
def computer_move(computer_score, human_score):
    
    """Returns the add-up score of computer's each round of rolling."""

    difference = human_score-computer_score
    print_round_count()
    print("Pig's turn!")
    global c_score
    c_score = 0
    c_list = []
    #computer rolls
    c_roll = roll()
    a = 1

    #computer rolls 4 times per turn by default
    #computer takes 2 more rolls for every 10 points behind
    if difference > 0:
        c_times = round(5 + difference*1/5)
    else:
        c_times = 5
        
    #if computer rolls 6, the score for the round turns 0
    #otherwise add the valid roll points to round score
    #show the player the list of computer's rolling results
    while c_roll != 6 and a < c_times:
        c_score += c_roll        
        c_list.append(c_roll)
        a += 1
        c_roll = roll()
    
    if c_roll == 6 and a < c_times:
        c_list.append(c_roll)
        c_score = 0
        
    print("Pig rolled", c_list)
    print("Pig scored", c_score, "in this round.")
    #add computer's round score to its total score
    computer_score += c_score
    c_str = "Pig rolled {} altogether.".format(computer_score)+"\n"
    print(c_str)
    print_line("-")
    
    global round_count
    round_count += 1
    return c_score


def human_move(computer_score, human_score):

    """Asks the player whether or not want to roll each time (except for the 1st).\
Returns the add-up score of human's each round of rolling."""

    print("Your turn!")
    global h_score
    h_score = 0
    #player rolls
    h_roll = roll() 
    print("You rolled a", h_roll)

    #for non-6 rolls, add them to the round score
    #for 6, turn the round score to 0
    if h_roll != 6:

        h_score += h_roll

        #ask for the player's instructions for 2nd and after 2nd rolls
        #roll if the player answers with y or Y
        while ask_yes_or_no("Roll again? (y/n)") == True:
            h_roll = roll()
            print("You rolled a", h_roll)

            if h_roll != 6:
                h_score += h_roll
            else:
                print("Oops, bad luck.")
                h_score = 0
                break
        
    else:
        print("Oops, bad luck.")
        h_score == 0

    print("You scored ",h_score, "in this round.")
    #add the player's round score to his/her total score
    human_score += h_score
    h_str = "You rolled {} altogether.".format(human_score)+"\n"
    print(h_str)
    print_line("-")

    return h_score


def show_current_status(computer_score, human_score):

    """Returns the results of comparing computer score with human score."""
    
    difference = human_score-computer_score

    print("Pig's score is {} and your score is {}.".format(computer_score,human_score))
    if difference < 0:
        print("Pig is",-difference, "point(s) ahead.")
    elif difference > 0:
        difference = human_score - computer_score
        print("You are", difference, "point(s) ahead.")
    elif difference == 0:
        print("The scores are tied.")


def is_game_over(computer_score, human_score):

    """Returns True if the game is considered over by its rules, otherwise False."""
    
    #if anyone reaches 50, the game is over
    if computer_score >= 50 or human_score >= 50:
        over = True
    #but if computer and human player tie at a score above 50, the game goes on
    elif computer_score >= 50 and computer_score == human_score or \
         human_score >= 50 and computer_score == human_score:
        over = False
    #when nobody reaches 50, the game goes on
    else:
        over = False
     
    return over


def show_final_results(computer_score, human_score):

    """Prints out the result of comparing computer's final score to human's final."""
    
    difference = human_score-computer_score
    if difference < 0:
        print("\n"+"Pig won by",-difference,"point(s). Good game.")
    elif difference > 0:
        print("\n"+"You won by",difference,"point(s). Congrats!")



def main():

    """Pig game starts execution."""
    
    #create a loop to repeat the game overtimes if the player wants to play
    play = 1
    while play == 1:

        #assign values to some local and global variables
        global round_count
        round_count = 1
        computer_score = 0
        human_score = 0
        difference = human_score-computer_score
        print_line("*")

        #use the computer_move, human_move, show_status functions
        #use computer_score += computer_move to assign value to computer_score
        computer_score += computer_move(computer_score, human_score)
        human_score += human_move(computer_score, human_score)
        show_current_status(computer_score, human_score)

        #if the winning requirements are not met, start a new round
        while is_game_over(computer_score, human_score) == False:
            
            computer_score += computer_move(computer_score, human_score)
            human_score += human_move(computer_score, human_score) 
            show_current_status(computer_score, human_score)
            is_game_over(computer_score, human_score)

        #if computer reaches 50 first and scores more than human player
        #give human player a bonus round to compensate for being at defensive position
        if is_game_over(computer_score, human_score) == True:
            
            if computer_score >= 50 and computer_score > human_score:
                
                print("Guess what? Bonus turn!")
                human_score += human_move(computer_score, human_score)
                print("Pig's score is {} and your score is {}.".format(computer_score,human_score))

                #just in case computer and human player tie after human's bonus round
                #so run is_game_over to check results against winning criteria again
                #if 2 players tie, go for another or more rounds until the tie breaks
                while is_game_over(computer_score, human_score) == False:
                    
                    computer_score += computer_move(computer_score, human_score)    
                    human_score += human_move(computer_score, human_score)
                    show_current_status(computer_score, human_score)
                    is_game_over(computer_score, human_score)
                    
        print("We have a winner!")

        #show who wins and by how much
        show_final_results(computer_score, human_score)

        #ask if the player wants to play one more time
        while ask_yes_or_no("Wanna play again? (y/n)") == False:
            play = 2
            print("See you next time. Bye-bye.")
            break

if __name__ == "__main__":
    main()




    






        
