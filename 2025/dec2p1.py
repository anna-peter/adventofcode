input = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528"

ranges = input.split(",")
for range in ranges:
    start, end = int(range.split("-")[0]), int(range.split("-")[1])
    print("start: "+str(start)+"; end: "+str(end))
    while (start<=end):
        comp = str(start)
        if (len(comp)%2 !=0):
            continue
        comp_len = len(comp)/2
        p1 = comp[:comp_len]
        p2 = comp[comp_len:]
        if p1==p2:
            print("yes")