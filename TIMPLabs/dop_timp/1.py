counter = 0
for x1 in range(2):
    for x2 in range(2):
        for x3 in range(2):
            for x4 in range(2):
                if (not x2 and x3 or not(x2 and x4)):
                    print(f'№{counter+1} | {x1} {x2} {x3} {x4}')
                counter +=1 
