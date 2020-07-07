package com.alibaba.balance;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author shenmeng
 */
public class CustomRule implements IRule {

    private ILoadBalancer lb;

    private List<Integer> excludePorts;

    public CustomRule() {
    }

    public CustomRule(List<Integer> excludePorts) {
        this.excludePorts = excludePorts;
    }

    @Override
    public Server choose(Object o) {
        //获取所有up状态的server
        List<Server> servers = lb.getReachableServers();
        //获取排除了指定端口的所有剩余的servers
        List<Server> availableServers = getAvailableServers(servers);
        //对剩余的servers随机方式获取一个
        return getAvailableRandomServer(availableServers);
    }

    private Server getAvailableRandomServer(List<Server> availableServers) {
        int index = new Random().nextInt(availableServers.size());
        return availableServers.get(index);
    }

    private List<Server> getAvailableServers(List<Server> servers) {
        if(CollectionUtils.isEmpty(excludePorts)){
            return servers;
        }
        List<Server> availableServers = new ArrayList<>();
        servers.forEach(server -> {
            if(!excludePorts.contains(server.getPort())){
                availableServers.add(server);
            }
        });
       // List<Server> serverList = servers.stream().filter(server -> excludePorts.stream().noneMatch(port -> server.getPort() == port)).collect(Collectors.toList());
        return availableServers;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.lb=iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
}
