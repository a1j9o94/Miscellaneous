#Since slacking is a dominant strategy, if I want to survive as long as possible, the only time I shouldn't slack is when
#m will not be reached by the other players. That is because if m is reached
# it will cancel out the food lost for slacking and even the -1 for hunting while an
#opponent slacks is less of a loss than slacking without the bonus.
#Consequently, most of my code is focused on predicting how many of my opponents will hunt.
#The more accurate that number is, the better I will be able to minimize the number of hunts I need.

# These will allow me to adjust my expected opponent hunts every round based on what it was the round before.
# It should make my predictions of hunting opponents to be more accurate as the game goes on.

#how many hunts I expected my opponents to do
predicted_hunts = 0

#the average amount per opponent that my expectation was off
adjustment_factor = 0

#an array of every amount per opponent that I was off by
difference = []

#keeps track of the current turn
turn_number = 1

#allows me to scale the adjustment factor based on the current number of opponents
num_players = 0

def calc_avg(list):
    total = 0
    for num in list:
        total += num
    avg = num/len(list)
    return avg

def hunt_choices(round_number, current_food, current_reputation, m,  player_reputations):
    global predicted_hunts
    global adjustment_factor
    global num_players
    num_players = len(player_reputations)
    opponent_hunts = 0
    hunt_decisions = []
    playerReputations = list(player_reputations)
    needed_hunts = 0
    for rep in playerReputations:
        opponent_hunts += (rep*len(playerReputations)) + adjustment_factor
    predicted_hunts = opponent_hunts
    needed_hunts = (m - opponent_hunts)
    hunt_decisions = ['s' for x in playerReputations]
    while needed_hunts > 0:
        max_index = playerReputations.index(max(playerReputations))
        hunt_decisions[max_index] = 'h'
        needed_hunts -= 1
        playerReputations[max_index] = 0
    return hunt_decisions
def hunt_outcomes(food_earnings):
    pass 

def round_end(award, m, number_hunters):
    global adjustment_factor
    global predicted_hunts
    global difference
    global turn_number
    global num_players
    #I don't change the adjustment factor on the first turn because the expected hunts will be zero and that will skew my results.
    if turn_number > 1:
        difference.append((number_hunters - predicted_hunts)/num_players)
        adjustment_factor = calc_avg(difference)
    turn_number += 1