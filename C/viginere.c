#include <stdlib.h>
#include <cs50.h>
#include <stdio.h>
#include <string.h>

int
main(int argc, char *argv[])
{
    if (argc != 2)
        {
            printf("Please only use one argument\n");
            return 1;
        }
    char *input = GetString();
    int holder = 0;
    int j = strlen(argv[1]);
    char *key = argv[1];
    int k[j];
    for (int i = 0; i < j; i++)
        {
            //turn letters into numbers
            k[i] = key[i];
            /* make ascii characters into 0 - 26 or don't change
               dont change if punctuation*/
            if ( k[i] > 64 && k[i] < 91 )
                k[i] = (k[i] - 65);
            else if (k[i] > 96 && k[i] < 123)
                k[i] = (k[i] - 97);
            else
                k[i] = k[i];
        }
    for (int i = 0, n = strlen(input); i < n; i++)
        {
            //rotate lower case letters
            if(65 <= input[i] && input[i] <= 90)
                {
                    input[i] = ((input[i] + k[holder])%91);
                    if (input[i] < 65)
                        input[i] = (input[i] + 65);
                    holder = (holder + 1);
                    if ( holder >= j )
                        holder = 0;
                }
            //rotate capital letters
            else if(97 <= input[i] && input[i] <= 122)
                {
                    input[i] = ((input[i] + k[holder])%123);
                    if (input[i] < 97)
                        input[i] = (input[i] + 97);
                    holder = (holder + 1);
                    if ( holder >= j )
                        holder = 0;
                }   
            // leave punctuation and spaces as is
            else
                {
                    input[i] = input[i];
                }
            printf("%c",input[i]);
        }
    printf("\n");
    return 0;
}