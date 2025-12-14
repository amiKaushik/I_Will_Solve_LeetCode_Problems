# Editorial Solution
class Solution {
    private static final int MOD = 1_000_000_007;

    private int count(int index, int seats, String corridor,
                      Map<Pair<Integer, Integer>, Integer> cache) {
        if (index == corridor.length()) {
            return seats == 2 ? 1 : 0;
        }

        Pair<Integer, Integer> key = new Pair<>(index, seats);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int result = 0;

        if (seats == 2) {
            if (corridor.charAt(index) == 'S') {
                result = count(index + 1, 1, corridor, cache);
            } else {
                result = (count(index + 1, 0, corridor, cache)
                        + count(index + 1, 2, corridor, cache)) % MOD;
            }
        } else {
            if (corridor.charAt(index) == 'S') {
                result = count(index + 1, seats + 1, corridor, cache);
            } else {
                result = count(index + 1, seats, corridor, cache);
            }
        }

        cache.put(key, result);
        return result;
    }

    public int numberOfWays(String corridor) {
        Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
        return count(0, 0, corridor, cache);
    }
}
