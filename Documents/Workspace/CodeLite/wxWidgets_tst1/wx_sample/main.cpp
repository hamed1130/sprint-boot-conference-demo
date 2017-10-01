//
// main.cpp - A simple wxWidgets GUI example
// Written by Ted Burke - last updated 25-2-2012
//

#include <wx/wx.h>
#include <string>


// The main window class
class MainFrame : public wxFrame {

	int counter = 0;
public:
	// Constructor
	MainFrame();

	// Controls
	wxStaticBitmap *image;
	wxStaticText *label;

	// Event handler functions
	void OnHelloButton(wxCommandEvent& WXUNUSED(event));
	void OnQuitButton(wxCommandEvent& WXUNUSED(event));
};


// Constructor for main window class
//
// NB The style flags passed to the parent class constructor
// create a non-resizeable window.
//
MainFrame::MainFrame()
	: wxFrame(NULL, wxID_ANY, "Hamed's wxWidgets Example",
	          wxPoint(0, 0), wxSize(640, 480),
	          wxDEFAULT_FRAME_STYLE & ~(wxRESIZE_BORDER|wxMAXIMIZE_BOX)) {
				  
	// Set window font. This will be inherited by child controls.
	SetFont(wxFont(32, wxFONTFAMILY_SWISS, wxFONTSTYLE_NORMAL, wxFONTWEIGHT_NORMAL));

	// Calculate values for positioning controls
	int w, h, h1, h2, h3, w1, w2;
	GetClientSize(&w, &h);
	h1 = 0.6 * h;
	h2 = 0.2 * h;
	h3 = h - h1 - h2;
	w1 = 0.5 * w;
	w2 = w - w1;

	// Create controls
	image = new wxStaticBitmap(this, wxID_ANY, wxBitmap("pattern.bmp", wxBITMAP_TYPE_BMP), wxPoint(0,0), wxSize(w, h1));
	label = new wxStaticText(this, wxID_ANY, "Example Text", wxPoint(0, h1), wxSize(w, h2), wxALIGN_CENTRE | wxST_NO_AUTORESIZE);
	
	wxButton *helloButton = new wxButton(this, wxID_ANY, "Hello", wxPoint(0, h1 + h2), wxSize(w1, h3));
	wxButton *quitButton = new wxButton(this, wxID_ANY, "Quit", wxPoint(w1, h1 + h2), wxSize(w2, h3));

	// Connect buttons to handler functions
	helloButton->Connect(wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler(MainFrame::OnHelloButton), NULL, this);
	quitButton->Connect(wxEVT_COMMAND_BUTTON_CLICKED, wxCommandEventHandler(MainFrame::OnQuitButton), NULL, this);

	// Display window on screen
	Show(true);
}

void MainFrame::OnHelloButton(wxCommandEvent& WXUNUSED(event)) {
	counter++;
	std::string str = "Number of clicks on this button: " + std::to_string(counter);
	wxMessageBox(str);
	label->SetLabel("Hello button clicked");
}

void MainFrame::OnQuitButton(wxCommandEvent& WXUNUSED(event)) {
	Close(true);
}

// The application class
class MyApp : public wxApp {
public:
	virtual bool OnInit();
};

bool MyApp::OnInit() {
	// Create window
	MainFrame *window = new MainFrame();

	return true;
}

IMPLEMENT_APP(MyApp);
