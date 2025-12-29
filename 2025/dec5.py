validIngredients = set()

secondPart = False
count = 0
end = -1

with open("dec5-input.txt", "r") as f:
    for line in f:
        if line == '\n':
            secondPart = True
        elif secondPart:
            # we reached the empty line
            ingredient = int(line.strip())
            # print("ingredient: "+ str(ingredient))
            if ingredient in validIngredients:
                count = count + 1
        else: 
            # we're still in the first part, read the valid ingredients
            line = line.strip()
            parts = line.split("-", 1)
            start = int(parts[0])
            if start < end:
                # overlapping range: the last range already covered the current start
                start = end
            end = int(parts[1])
            # print("start: "+str(start)+" end: "+str(end))
            
            for i in range(start, end+1):
                validIngredients.add(i)

print("count: "+str(count))
        

