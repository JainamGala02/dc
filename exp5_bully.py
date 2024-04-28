def bully_election(n, processes):
    crashed_processes = [i + 1 for i, status in enumerate(processes) if status == 0]
    running_processes = [i + 1 for i, status in enumerate(processes) if status == 1]
    
    if not running_processes:
        print("*** ALL Processes Are Crashed ***")
        initiator = n
        print(f"Initiating election with process {initiator} as initiator")
    else:
        initiator = max(running_processes)
        print(f"Initiating election with process {initiator} as initiator")
    
    print("Processes called for election:")
    for process in range(initiator, n + 1):
        print(f"Process {process}")
    
    print("\nProcess statuses:")
    for process, status in enumerate(processes, start=1):
        if status == 0:
            print(f"Process {process} is Dead")
        else:
            print(f"Process {process} is In")
    
    new_coordinator = max(running_processes) if running_processes else None
    if new_coordinator:
        print(f"\n*** New Coordinator Is {new_coordinator} ***")
    
    return new_coordinator

def main():
    n = int(input("Enter the number of processes: "))
    processes = [1] * n  # Initialize all processes as running
    coordinator = n  # Initially, the process with the highest ID is the coordinator
    
    while True:
        choice = input("\n1. Crash a Process\n2. Recover a Process\n3. Display New Coordinator\n4. Exit\nEnter your choice: ")
        
        if choice == "1":
            process_id = int(input("Enter the process ID to crash: "))
            if 1 <= process_id <= n:
                if processes[process_id - 1] == 1:
                    processes[process_id - 1] = 0
                    print(f"Process {process_id} has been crashed")
                    if process_id == coordinator:
                        coordinator = bully_election(n, processes)
                else:
                    print(f"Process {process_id} is already crashed")
            else:
                print("Invalid process ID")
        
        elif choice == "2":
            crashed_processes = [i + 1 for i, status in enumerate(processes) if status == 0]
            if crashed_processes:
                print("Crashed processes are:", ", ".join(map(str, crashed_processes)))
                process_id = int(input("Enter the process ID to recover: "))
                if 1 <= process_id <= n:
                    if processes[process_id - 1] == 0:
                        processes[process_id - 1] = 1
                        print(f"Process {process_id} has been recovered")
                        if process_id > coordinator:
                            coordinator = process_id
                            print(f"Process {coordinator} is the new coordinator")
                    else:
                        print(f"Process {process_id} is not crashed")
                else:
                    print("Invalid process ID")
            else:
                print("No crashed processes")
        
        elif choice == "3":
            print(f"Current coordinator is Process {coordinator}")
        
        elif choice == "4":
            break
        
        else:
            print("Invalid choice")

if __name__ == "__main__":
    main()