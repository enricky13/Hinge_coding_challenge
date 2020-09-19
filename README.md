# Hinge_coding_challenge

What Architecture is used : VIPER

Reason: I considered that I would be saving data often in the app and wanted to separate how it would be saved along with when. I also saw that multiple buttons and views were to be updated depending on the situation and wanted to keep the view class strictly for displaying. I knew it would be possible to juggle between saving and loading data without all the logic to be held in one class and wanted to ensure if I needed to find an alternate way to save or load, I can do it without other classes being affected too much.
I also implemented an extra interface, InteractorOutput, this was to differentiate which method(s) were being called because of data being loaded, rather than keeping the method in the regular interactor class. Although there is no other activity running, I have a router class and can easily switch between activities if one is to be implemented in the future. The view class I implemented follows the principles of VIPER which is strictly for manipulating what the user sees. 
