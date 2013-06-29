/***************************************************************************
 * fifteen.c
 *
 * Computer Science 50
 * Problem Set 3
 *
 * Implements The Game of Fifteen (generalized to d x d).
 *
 * Usage: fifteen d
 *
 * whereby the board's dimensions are to be d x d,
 * where d must be in [DIM_MIN,DIM_MAX]
 *
 * Note that usleep is obsolete, but it offers more granularity than
 * sleep and is simpler to use than nanosleep; `man usleep` for more.
 ***************************************************************************/
 
#define _XOPEN_SOURCE 500

#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>


// constants
#define DIM_MIN 1
#define DIM_MAX 9


// dimensions
int d;


// board
int board[DIM_MAX][DIM_MAX];
int max_value;

// prototypes
void clear(void);
void greet(void);
void init(void);
void draw(void);
bool move(int tile);
bool won(void);
bool search(int value, int values[], int n);


int
main(int argc, char *argv[])
{
    // greet user with instructions
    greet();

    // ensure proper usage
    if (argc != 2)
    {
        printf("Usage: fifteen d\n");
        return 1;
    }

    // ensure valid dimensions
    d = atoi(argv[1]);
    if (d < DIM_MIN || d > DIM_MAX)
    {
        printf("Board must be between %d x %d and %d x %d, inclusive.\n",
         DIM_MIN, DIM_MIN, DIM_MAX, DIM_MAX);
        return 2;
    }
    max_value = ((d*d) - 1);

    // initialize the board
    init();

    // accept moves until game is won
    while (true)
    {
        // clear the screen
        clear();

        // draw the current state of the board
        draw();

        // check for win
        if (won())
        {
            printf("ftw!\n");
            break;
        }

        // prompt for move
        printf("Tile to move: ");
        int tile = GetInt();

        // move if possible, else report illegality
        if (!move(tile))
        {
            printf("\nIllegal move.\n");
            usleep(500000);
        }

        // sleep thread for animation's sake
        usleep(500000);
    }

    // that's all folks
    return 0;
}


/*
 * Clears screen using ANSI escape sequences.
 */

void
clear(void)
{
    printf("\033[2J");
    printf("\033[%d;%dH", 0, 0);
}


/*
 * Greets player.
 */

void
greet(void)
{
    clear();
    printf("WELCOME TO THE GAME OF FIFTEEN\n");
    usleep(2000000);
}


/*
 * Initializes the game's board with tiles numbered 1 through d*d - 1
 * (i.e., fills 2D array with values but does not actually print them).  
 */

void
init(void)
{
    int holder = max_value;
    for(int i = 0; i < d; i++)
    {
        for(int j = 0; j < d; j++)
        {
            if( i == (d-1) && j == (d-1))
            {
                board[i][j] = 0;
                break;
            }
            else
            {
                board[i][j] = holder;
                holder = (holder - 1);
            }
        }
    }
}


/* 
 * Prints the board in its current state.
 */

void
draw(void)
{
    for(int i = 0; i < d; i++)
    {
        for(int j = 0; j < d; j++)
        {
            if(board[i][j] != 0)
                printf("%3d", board[i][j]);
            else
                printf("   ");
        }
        printf("\n");
    }
}


/* 
 * If tile borders empty space, moves tile and returns true, else
 * returns false. 
 */

bool
move(int tile)
{
    for( int i = 0; i < d; i++)
    {
        for(int j = 0; j < d; j++)
        {
            if(board[i][j] == tile)
            {
                if(board[i + 1][j] == 0 && ((i + 1) < d))
                {
                    board[i + 1][j] = board[i][j];
                    board[i][j] = 0;
                    return true;
                }
                else if( board[i - 1][j] == 0 && ((i - 1) >= 0))
                {
                    board[i - 1][j] = board[i][j];
                    board[i][j] = 0;
                    return true;
                }
                else if( board[i][j + 1] == 0 && ((j + 1) < d))
                {
                    board[i][j + 1] = board[i][j];
                    board[i][j] = 0;
                    return true;
                }
                else if( board[i][j - 1] == 0 && ((j - 1) >= 0))
                {
                    board[i][j - 1] = board[i][j];
                    board[i][j] = 0;
                    return true;
                }
                else
                    break;
            }
        }
        
    }                
    return false;
}


/*
 * Returns true if game is won (i.e., board is in winning configuration), 
 * else false.
 */

bool
won(void)
{
    int holder[d][d];
    int holder1 = 1;
    for(int i = 0; i < d; i++)
    {
        for(int j = 0; j < d; j++)
        {
            if((i*j) != (d*d))
            {
                holder[i][j] = holder1;
                holder1 = (holder1 + 1);
            }
            else 
                holder[i][j] = 0;        
        }
    }
    for(int i = 0; i < d; i++)
    {
        for(int j = 0; j < d; j++)
        {
            if(board[i][j] != holder[i][j])
                break;
            else if(((i*j) == (d*d)) && (board[i][j] == 0) && (holder[i][j] == 0))
                return true;
        }
    }    
    return false;
}