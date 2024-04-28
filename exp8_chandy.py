class Message:
    def __init__(self, initiator, source, destination):
        self.initiator = initiator
        self.source = source
        self.destination = destination

    def __str__(self):
        return f"({self.initiator},{self.source},{self.destination})"

def chandy_haas_misra():
    print("Enter the number of processes")
    n = int(input())
    graph = [[0] * n for _ in range(n)]
    print("Enter the wait for graph:")
    for i in range(n):
        for j in range(n):
            graph[i][j] = int(input())

    print("The wait for graph is:")
    display(graph)

    print("Enter the process initiating probe")
    init = int(input())
    print("Initiating probe...")

    mess_list = []
    count = 0
    for i in range(n):
        for j in range(n):
            if graph[i][j] == 1:
                m = Message(init, i, j)
                mess_list.append(m)
                count += 1

    print(mess_list)

    is_deadlock = False
    for i in range(count):
        for j in range(count):
            if mess_list[i].initiator == mess_list[j].destination:
                is_deadlock = True
                break

    if is_deadlock:
        print("The Deadlock has been detected...")
    else:
        print("No Deadlock has been detected...")

def display(mat):
    n = len(mat[0])
    m = len(mat)
    for i in range(m):
        for j in range(n):
            print(mat[i][j], end=" ")
        print()

if __name__ == "__main__":
    chandy_haas_misra()