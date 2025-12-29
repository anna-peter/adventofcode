import networkx as nx
import matplotlib.pyplot as plt
print("Hello world")

G = nx.DiGraph()

with open("dec11-input.txt", "r") as f:
    for line in f:
        line = line.strip()
        parts = line.split(":", 1)

        source = parts[0].strip()
        dest_nodes = parts[1].strip().split()

        # print(line)
        print("source " + source + " and dests " + " ".join(dest_nodes))

        for dest in dest_nodes:
            G.add_edge(source, dest)

    # shortest_path = nx.shortest_path(G, source='you', target='out')
    all_paths = list(nx.all_simple_paths(G, source='you', target='out'))
    print(len(all_paths))

# nx.draw(G, with_labels=True)
# plt.show()
