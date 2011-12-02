#!/usr/bin/env ruby
if ARGV[0] == 'total'
	system("free -mto | grep Mem: | awk '{ print $2 }'");
elsif ARGV[0] == 'used'
	system("free -mto | grep Mem: | awk '{ print $3 }'");
elsif ARGV[0] == 'free'
	system("free -mto | grep Mem: | awk '{ print $4 }'");
else
	print 0
end
