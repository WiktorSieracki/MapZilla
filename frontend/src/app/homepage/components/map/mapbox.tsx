import { MakeNodes } from '@/app/homepage/components/map/make-nodes';
import { MakePolygons } from '@/app/homepage/components/map/make-polygons';
import { configureMapIcons } from '@/app/homepage/components/map/map-config';
import { useCenterContext } from '@/app/homepage/context/center-context';
import { CenterContextProps } from '@/app/homepage/interface/center-interface';
import { Button } from '@/components/ui/button';
import 'leaflet/dist/leaflet.css';
import {
  MapContainer,
  Marker,
  Popup,
  TileLayer,
  useMapEvents,
} from 'react-leaflet';

configureMapIcons();

function LocationMarker({ setCenter }: CenterContextProps) {
  useMapEvents({
    click(e) {
      setCenter({ x: e.latlng.lat, y: e.latlng.lng });
    },
  });
  return null;
}

const MapBox = () => {
  const { center, setCenter, data, setData, searchKey, setSearchKey } =
    useCenterContext();

  return (
    <div>
      <div className="h-[600px] w-[600px]">
        <MapContainer
          key={searchKey}
          center={[center.x, center.y]}
          zoom={13}
          scrollWheelZoom={true}
          style={{ height: '100%', width: '100%' }}>
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <Marker position={[center.x, center.y]}>
            <Popup>
              You are here. <br />
            </Popup>
          </Marker>
          {data && <MakePolygons elements={data.elements} />}
          {data && <MakeNodes elements={data.elements} />}
          <LocationMarker
            center={center}
            setCenter={setCenter}
            data={data}
            setData={setData}
            searchKey={searchKey}
            setSearchKey={setSearchKey}
          />
        </MapContainer>
      </div>
      <Button
        className="m-1"
        onClick={() =>
          setCenter({ x: 54.39482637467512, y: 18.574318885803226 })
        }>
        Put possition on UG
      </Button>
    </div>
  );
};

export default MapBox;
