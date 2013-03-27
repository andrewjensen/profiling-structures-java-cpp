
compile:
	g++ -Wall -O2 Profiler.cpp *.h -o profiler
	g++ -Wall -O2 Tests.cpp *.h -o tests
	
test:
	@./tests
	
profile:
	@./profiler
	
clean:
	rm tests
	rm profiler
	
a:
	@g++ -Werror -Wall -O2  Profiler.cpp *.h -o profiler
	@./profiler
	