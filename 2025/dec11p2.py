import networkx as nx
import matplotlib.pyplot as plt

G = nx.DiGraph()
count = 0

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
    # svr_to_dac = list(nx.all_simple_paths(G, source='svr', target='dac'))
    # svr_to_fft = list(nx.all_simple_paths(G, source='svr', target='fft'))

    # fft_to_dac
    # dac_to_fft

    # dac_to_out = list(nx.all_simple_paths(G, source='svr', target='dac'))
    # fft_to_out

svr_to_out = list(nx.all_simple_paths(G, source='svr', target='out'))

for path in svr_to_out:
    if 'fft' in path and 'dac' in path:
        count = count + 1
    # print(path)
        

print(count)

# nx.draw(G, with_labels=True)
# plt.show()
