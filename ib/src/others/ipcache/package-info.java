/**
 * <p>
 * This package contains implementation of the solution for the problem -
 * Given a list of ipaddress ranges and the countries those ranges belong to, come up with a way to find the country of
 * a given ipaddress. The given data could be huge.
 * </p>
 * <code>
 * Eg.<br>
 * CityA - 10.40.80.60 - 10.50.80.70<br>
 * CityB - 20.50.20.11 - 20.70.11.60<br>
 *
 * Search for 10.41.80.60 -> return CityA<br>
 * 20.51.70.80 -> return CityB<br>
 * 11.50.50.60 -> return ""<br>
 *</code>
 * <p>
 * Solution implemented - Let's take 32 bit ip addresses. Break them in 4 parts of 8 bits each. The first three parts are
 * used to navigate a tree based index which we will create and the last part would be used for storing/finding the city/country.
 * </p>
 * <p>
 * Consider a tree of three levels (for 32 bit ip addresses). Each node of the tree can have up to 256 children i.e. one child
 * branch for each of the 256 possible values (0-255) of the 8 bit part of ipaddress. The leaf nodes won't have any children but
 * they store the values of cities/countries in a map whose keys will be in the range 0-255.
 * </p>
 * <p>
 * There are two options for storage here - keep an array of size 256 at each level. Since non leaf nodes will allocate place
 * for references of children and leaf nodes would store string references. If we create array before hand then there would be one
 * reference per possible ip address in the leaf nodes.<br>
 * Assuming 64 bit addresses/references.<br>
 * Total size of city array = 2^32 * 8 bytes = approx 4*10^9 * 8 bytes = 32 * 10^9 bytes = 32 GB
 * </p>
 *
 * <i>And we haven't yet considered the child node references :)</i>
 * <p>
 * So, either machine should be beefy, or we could shard the index. We could do the sharding based on first part (first 8 bits)
 * of the IPs. 0-63 on one machine, 64-127 on second, 128-191 on third, and 192-255 on fourth.
 * </p>
 * Other option (which is implemented in this package) is to keep the child/city references sparse. We need a sorted list of children
 * (sorted according to the ip part).<br>
 *
 * How to insert an entry - (city, startIP, endIP)
 * <ol>
 *     <li>Obtain first 24 bits of startIP and endIP and store in s24, e24 respectively.</li>
 *     <li>For each 8 bit part of s24, traverse the tree, inserting a child node if needed.</li>
 *     <li>For each 8 bit part of e24, traverse the tree, inserting a child node if needed.</li>
 *     <li>In the last node inserted for s24, add an entry to citymap whose key would be last 8 bits of startIP and value would be
 *     "S-"+city</li>
 *     <li>In the last node inserted for e24, add an entry to citymap whose key would be last 8 bits of endIP and value would be
 *     "E-"+city</li>
 * </ol>
 *
 * So, there are two values inserted for each input - startIP position would contain "S-"+city and endIP position would contain "E-"+city.
 *
 * How to search for an IP - searchIP
 * <ol>
 *     <li>Obtain first 24 bits of searchIP and store in s24.</li>
 *     <li>Iterator on the 8 bit parts of s24, and keep finding the child nodes in the tree.</li>
 *     <li>Till the point exact branches are found, keep going.</li>
 *     <li>If at a point, exact match is not found, find the child which is stored at the greatest key less than (floor)
 *     the key you're looking for </li>
 *     <li>If, still, no child is found, return with empty result</li>
 *     <li>If finding the floor entry worked, find the right most child of the tree starting with the floor entry</li>
 *     <li>In the leaf node, look for the key which is floor(less than or equal to) of the last 8 bits of searchIP</li>
 *     <li>If no entry found is start of city, return empty</li>
 *     <li>If entry found is start of city, that is the answer</li>
 *     <li>If entry found is end of a city, the entry's key should match the last 8 bits of the searchIP. Else answer is empty.</li>
 * </ol>
 *
 */
package others.ipcache;