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
    //chang input value to int
    int k = atoi(argv[1]);
    char *p = GetString();
    //make k less than 26
    while ( k > 26)
        k = (k - 26);
    for(int i = 0, n = strlen(p); i < n; i++)
        {
            if(65 <= p[i] && p[i] <= 90)
                {
                    p[i] = ((p[i] + k)%91);
                    if (p[i] < 65)
                        p[i] = (p[i] + 65);
                }
            else if(97 <= p[i] && p[i] <= 122)
                {
                     p[i] = ((p[i] + k)%123);
                     if (p[i] < 97)
                        p[i] = (p[i] + 97);
                }   
            else
                {
                    p[i] = p[i];
                }
            printf("%c", p[i]);
        }
    printf("\n");
    return 0;
}