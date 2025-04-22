"""
It's example of greedy algorithm using as approximation algorithm to solve the set covering problem (NP problem).
Complexity: O(n^2) instead of O(2^n) for accurate solution (n is the number of stations)

Set covering problem:
Given a set of states and a set of stations, find the minimum set of stations that cover all states.
"""

states_needed = {"mt", "wa", "or", "id", "nv", "ut", "ca", "az"}

stations = {
    "kone": {"id", "nv", "ut"},
    "ktwo": {"wa", "id", "mt"},
    "kthree": {"or", "nv", "ca"},
    "kfour": {"nv", "ut"},
    "kfive": {"ca", "az"}
}


def greedy_set_cover(states_needed, stations):
    final_stations = set()
    while states_needed:
        best_station = None
        states_covered = set()
        for station, states_for_station in stations.items():
            covered = states_needed & states_for_station
            if len(covered) > len(states_covered):
                best_station = station
                states_covered = covered
        states_needed -= states_covered
        final_stations.add(best_station)
    return final_stations


print(greedy_set_cover(states_needed, stations))  # {'ktwo', 'kfive', 'kthree', 'kone'}
