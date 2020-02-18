#Ziyu Zhao
#71435169

#Resources used:
#googled some function use detials occationally when needed

#1.interface default functions
import random

#1.1 define a line printer for interface
def print_line(char):
    """Draws cut-off lines to make the interface clearer-looking""" 
    print(char * 56)

#1.2 to keep track of the number of moves    
move_count = 1
def print_move_count():
    """Prints the count of moves"""
    global move_count
    print("\n"+"                    # Move",move_count,"#"+"\n")
    
#1.3 to give response to user input
def ask_yes_or_no(prompt):
    """Returns true if the player answers the prompt question with y or Y,\
and returns False if the player's answer starts with n or N. Ask the player\
to answer again if his/her response starts with other letters or numbers."""
    demand = str(input(prompt)).upper()
    while demand[0] != "Y" and demand[0] != "N":
        demand = str(input("Please enter y/n")).upper()
    if demand[0] == "Y":
        answer = True
    elif demand[0] == "N":
        answer = False
    return answer

#1.4 to check if the input is a number when a numeric input is required
def input_valid(abc):
    """Return the boolean result of whether user's input is a number as required"""
    try:
        int(abc)
        return True
    except ValueError:
        return False

#2.tower blaster functions
#2.1 to give instructions
def print_instructions():
    """Hello Avenger! \nWelcome to Avengers: Infinity Tower Blaster! \nIn this game, \
you will compete against Thanos. Whoever achieves tower stabality fisrt wins! \
By tower stability, we mean to have the bricks arranged from the smallest number to the biggest \
top-down in order.\nWait no more! Let's play!\n"""
print(print_instructions.__doc__)

#2.2 as named
main_pile = []
discard_pile = []
def setup_bricks():
    """Returns two lists representing main pile, a list 60 bricks, \
and discard pile of bricks, an empty list at this point"""
    for x in range(1,61):
        main_pile.append(x)
    return (main_pile,discard_pile)

#2.3 as named
def shuffle_bricks(bricks):
    """Shuffles the pile"""
    random.shuffle(bricks)

#2.4 check if there are any cards left in the given main pile of bricks
def check_bricks(main_pile,discard):
    """Re-assign value to main pile bricks and discard pile after the main pile runs out of bricks"""
    if len(main_pile) == 0:
        random.shuffle(discard)
        main_pile = discard
        discard = [main_pile[0]]
        main_pile.pop(0)
    return (main_pile,discard)

#2.5 given a tower, determine if stability has been achieved.
def check_tower_blaster(tower):
    """Returns the boolean result of whether the given tower has achieved stability"""
    if tower == sorted(tower):
        return True
    else:
        return False

#2.6 remove and return the top brick from any given pile of bricks
def get_top_brick(brick_pile):
    """Returns the top brick of certein pile"""
    top_brick = int(brick_pile[0])
    brick_pile.pop(0)
    return top_brick

#2.7 deal initial bricks
computer_pile = []
human_pile = []
def deal_initial_bricks(main_pile):
    """Deals two sets of 10 bricks for computer pile and human pile from main pile"""
    a = 0
    while a < 10:
        computer_pile.insert(0,get_top_brick(main_pile))
        human_pile.insert(0,get_top_brick(main_pile))
        a += 1
    piles = (computer_pile, human_pile)
    return piles

#2.8 as named
def add_brick_to_discard(brick,discard):
    """Adds the given brick (represented as an integer) to the top of the given discard 
pile"""
    discard.insert(0,brick)
    
#2.9 to process user's input for choosing bricks from discard pile or randonly from main pile
def choose_brick():
    """Returns the brick the user wants to pick for his/her tower"""
    choose_pile = input("The top brick in discard pile is {}.".format(discard_pile[0])\
                        +"\nEnter 'D' to take it.\nEnter 'M' for a mystery brick.").upper()
    while choose_pile != "D" and choose_pile != "M":
        choose_pile = input("Please enter 'D' or 'M'.").upper()
    if choose_pile == "D":
        new_brick = get_top_brick(discard_pile)
    elif choose_pile == "M":
        new_brick = get_top_brick(main_pile)
        print("Mystery brick:",new_brick)
        if ask_yes_or_no("Do you want to use this brick? 'Y' to use / 'N' to skip move.") == True:
            pass
        else:
            add_brick_to_discard(new_brick,discard_pile)
            new_brick = None
            pass
    return new_brick

#2.10 ask for user's input on which brick to replace with the chosen one
def to_replace(tower):
    """Returns the number on the brick that the user wants to replace"""
    brick_to_be_replaced = input(\
    "Please enter the number of the brick you want to replace.")
    while input_valid(brick_to_be_replaced) == False:
        brick_to_be_replaced = input(\
        "Please enter a number existing in your tower.")
    while int(brick_to_be_replaced) not in tower:
        brick_to_be_replaced = int(input(\
        "Please enter a number existing in your tower."))   
    return int(brick_to_be_replaced)

#2.11 find the given brick to be replaced in the given tower and replace it with the given new brick
def find_and_replace(new_brick,brick_to_be_replaced,tower,discard):
    """Checks whether the wanted brick to be replaced is properly replaced"""
    tower[tower.index(brick_to_be_replaced)] = new_brick
    add_brick_to_discard(brick_to_be_replaced,discard)
    if brick_to_be_replaced in tower:
        return True
    else:
        return False
    
#2.12 human_play() consists of the part of asking for input and the part of processing input
def human_play():
    """This function is where human decision making process take place. The user has to deciside\
 to replace which number and with which number to finish the replacement. Returns the human pile after\
 one round of replacement."""
    print("\n"+"Avengers' move!")
    print("Avengers' tower:", human_pile)
    new_brick = choose_brick()
    if new_brick != None:
        brick_to_be_replaced = to_replace(human_pile)
        find_and_replace(new_brick,brick_to_be_replaced,human_pile,discard_pile)
    else:
        print("You skipped this turn.")
        pass
    print("Avengers' tower now:",human_pile)
    return human_pile

#2.13 a simple grouping function for the use in computer play strategy
def group_number(x):
    """Returns the order number of the group that a certain brick shoule be categorized into"""
    #60 is a special number as it's the only one should be logically put in group 10, add it to group 9
    if x == 60:
        group_number = 9
    else:
        group_number = int(x/6)
    return group_number

#2.14 computer strategy
##the main strategy is to allocate numbers into groups with range of 6 
##(except for the 1st group, with an interval of 5 only, and last group of 7)
###the ideal is to have one number in each group to represent a whole interval it belongs in
###but as it is too rigid and may take too many moves waiting for one right piece
###the strategy also allows 2 or 3 from the same group to co-exist in the list
####the co-existence could be replaced with an ideal group representative in later moves, but as the check_blaster may
#####take place before the replacement, it's an overall move-saving step
######i didn't use max and min to allow more than 3 numbers from a same group to co-exist, as to avoid using dictionary
######(also makes it fairly easier for users to win)
def computer_play(tower,main_pile,discard):
    """Returns computer pile after one round of automatically brick replacement"""
    lst = []   
    print("Thanos' move!")

    #make a list of the indices of the numbers already placed on their supposed-to-be positions
    #such as 3 in grou 1-5, 8 in group 6-11, etc
    for i in tower:
        if group_number(i) == tower.index(i):
            lst.append(tower.index(i))
            
    #get the top of discard pile to start a choice
    new_brick = get_top_brick(discard)
    
    #if another number already took the top discard brick's group box
    if group_number(new_brick) in lst:
        
        #put the top of discard back, as it was taken down for getting_the_top function
        #i didn't go further on placing 2 in same group step here, as the priority is one for each group
        add_brick_to_discard(new_brick,discard)
        #pick the top brick of main pile
        new_brick = get_top_brick(main_pile)
        print("Thanos chose a mystery brick:",new_brick)
        
        if group_number(new_brick) in lst:
            #if the group number of new brick is 0 or 9, the meaning of place one top with another is not
            #that great (only slightly made more room for the left blanks), so I ruled them out
            
            if group_number(new_brick) != 0 and group_number(new_brick) != 9:
                
                #to allow 2 from the same group to co-exist
                #if the new brick number is larger than the brick already there
                #and the number for the next following group is missing, place it to its right
                if group_number(new_brick)+1 not in lst and new_brick > tower[group_number(new_brick)]:
                    tower.insert(group_number(new_brick)+1,new_brick)
                    discard.insert(0,tower[group_number(new_brick)+2])
                    tower.pop(group_number(new_brick)+2)
                    print("Thanos replaced a brick.")

                #if the new brick number is smaller than the brick already there
                #and the number for the next group before is missing, place it to its left                    
                elif group_number(new_brick)-1 not in lst and new_brick < tower[group_number(new_brick)]:
                    tower.insert(group_number(new_brick)-1,new_brick)
                    discard.insert(0,tower[group_number(new_brick)])
                    tower.pop(group_number(new_brick))
                    print("Thanos replaced a brick.")
                    
                #if it's unnecessary to do the replacement, i.e the next group box is already taken
                #skip the turn
                else:
                    discard_pile.insert(0,new_brick)
                    new_brick = None
                    print("Thanos skipped this turn.")
                    pass
                
            #for two-ends-group numbers, skip the turn,a explained before
            else:
                discard_pile.insert(0,new_brick)
                new_brick = None
                print("Thanos skipped this turn.")
                pass
            
        #if the group of chosen number from main pile doesn't have a representative, replace
        else:
            tower.insert(group_number(new_brick),new_brick)
            discard.insert(0,tower[group_number(new_brick)+1])
            tower.pop(tower.index(new_brick)+1)
            print("Thanos replaced a brick.")
            
    #if the group of top brick from discard pile doesn't have a representative, replace
    else:
        print("Thanos took {} from the discard pile.".format(new_brick))
        tower.insert(group_number(new_brick),new_brick)
        discard.insert(0,tower[group_number(new_brick)+1])
        tower.pop(tower.index(new_brick)+1)
        print("Thanos replaced a brick.")      
    return computer_pile

#3 main function where every functions're put together
def main():
    """Avengers: Infinity Tower Blaster officially begins!"""
    #ask for more rounds of play
    play = 1
    while play == 1:
        
        #assign values to varialbes and lists initially
        global main_pile
        global discard_pile
        global computer_pile
        global human_pile
        global move_count
        main_pile = []
        discard_pile = []
        computer_pile = []
        human_pile = []
        move_count = 1
        
        #functions
        setup_bricks()
        shuffle_bricks(main_pile)
        piles = deal_initial_bricks(main_pile)
        computer_pile = (piles[0])
        human_pile = (piles[1])
        #assign the top of main pile to be the fisrt brick in discard pile
        add_brick_to_discard(main_pile[0],discard_pile)
        main_pile.pop(0)
        print_line("*")
        print("\nThanos' tower:",computer_pile)
        print("Avengers' tower:",human_pile)
        
        #when no pile meets the tower blaster requirements, the game goes on
        while check_tower_blaster(computer_pile) == False and check_tower_blaster(human_pile) == False:
            print_move_count()
            computer_play(computer_pile,main_pile,discard_pile)
            human_play()
            #if the main pile runs out of bricks, reset the number
            (main_pile, discard_pile) = check_bricks(main_pile,discard_pile)
            check_tower_blaster(computer_pile)
            check_tower_blaster(human_pile)
            move_count += 1
            print_line("-")
            
        #though it is very rare, but it's possible that both pile finish tower blaster after same number of moves
        #it is unfair to cut the game once the computer pile is done, so I let them always take same number of moves
        if check_tower_blaster(computer_pile) == True and check_tower_blaster(human_pile) == True:
            print("Thanos and Avengers had a tie! \nThanos and Avengers made peace with each other and lived happily ever after.")
            print("\nThanos' final tower:",computer_pile)
            print("Avengers' final tower:",human_pile)
        else:
            #if a pile is done, it wins
            if check_tower_blaster(computer_pile) == True:
                print("\n"+"Thanos won! Boom to Avengers' tower! \nThanos: I am (long pause) inevitable!")
                print("\nThanos' final tower:",computer_pile)
                print("Avengers' final tower:",human_pile)
            if check_tower_blaster(human_pile) == True:
                print("\n"+"Avengers won! Boooooom to Thanos' tower! \nThanos dissolving into dust...")
                print("\nThanos' final tower:",computer_pile)
                print("Avengers' final tower:",human_pile)

        while ask_yes_or_no("\nUse the time stone to start over again? (y/n)") == False:
            play = 2
            print("\nSee you next time. Bye-bye.")
            break

if __name__ == '__main__':
    main()


