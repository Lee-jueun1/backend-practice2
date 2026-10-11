
package com.example.backendpractice1.member;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MemberService {

    private final AtomicLong sequence = new AtomicLong(0);

    private final ConcurrentMap<Long, Member> members =
            new ConcurrentHashMap<>();

    public Member createMember(String name, String email) {
        Long id = sequence.incrementAndGet();

        Member member = new Member(id, name, email);
        members.put(id, member);

        return member;
    }
    public java.util.List<Member> getAllMembers() {
        return java.util.List.copyOf(members.values());
    }
    public Member updateMember(Long id, String name, String email) {
        Member existingMember = members.get(id);

        if (existingMember == null) {
            return null;
        }

        Member updatedMember = new Member(id, name, email);
        members.put(id, updatedMember);

        return updatedMember;
    }
    public void deleteMember(Long id) {
        Member removedMember = members.remove(id);

        if (removedMember == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "회원을 찾을 수 없습니다."
            );
        }
    }
}
